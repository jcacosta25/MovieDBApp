package io.jcal.theMovie.presentation.ui.home.viewmodel.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.jcal.movies_provider.domain.interactor.UseCaseGetPopularMovies
import io.jcal.movies_provider.domain.interactor.base.Status
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesDataSource(
    private val scope: CoroutineScope,
    private val useCaseGetPopularMovies: UseCaseGetPopularMovies,
    private val mapper: PresentationDataMapper
) : PageKeyedDataSource<Int, MovieUIModel>() {
	
	private var retry: (() -> Any)? = null
	private val resourceStatus: MutableLiveData<Map<Status, Int>> = MutableLiveData()
	
	// we need to observe these
	private val initialLoad: MutableLiveData<Map<Status, Int>> = MutableLiveData()
	
	fun retryAllFailed() {
		val prevRetry = retry
		retry = null
		prevRetry?.invoke()
	}
	
	val resourceState: LiveData<Map<Status, Int>> = resourceStatus
	val refreshState: LiveData<Map<Status, Int>> = initialLoad
	
	fun clearCoroutineJobs() {
		scope.cancel()
	}
	
	override fun loadInitial(
	    params: LoadInitialParams<Int>,
	    callback: LoadInitialCallback<Int, MovieUIModel>
	) {
		scope.launch {
			useCaseGetPopularMovies.execute().collect {
				// onResult(@NonNull List<Value> data, int position, int totalCount,
				//                 @Nullable Key previousPageKey, @Nullable Key nextPageKey)
				when (it.state) {
					BaseModel.SUCCESS -> {
						mapper.convert(it).let { movies ->
							callback.onResult(
								movies.results,
								movies.page,
								movies.totalPages,
								null,
								movies.page.inc()
							)
							mapOf(Pair(Status.SUCCESS, it.errorCode)).run {
								resourceStatus.postValue(this)
								initialLoad.postValue(this)
							}
						}
					}
					BaseModel.ERROR -> {
						retry = {
							loadInitial(params, callback)
						}
						mapOf(Pair(Status.ERROR, mapper.getErrorMessage(it.errorCode))).run {
							resourceStatus.postValue(this)
							initialLoad.postValue(this)
						}
					}
					else -> {
						mapOf(Pair(Status.LOADING, it.errorCode)).run {
							resourceStatus.postValue(this)
							initialLoad.postValue(this)
						}
					}
				}
			}
		}
	}
	
	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {
		scope.launch {
			useCaseGetPopularMovies.execute(params = UseCaseGetPopularMovies.Params(params.key))
				.collect {
					when (it.state) {
						BaseModel.SUCCESS -> {
							val movies = mapper.convert(it)
							callback.onResult(movies.results, movies.page.inc())
							mapOf(Pair(Status.SUCCESS, it.errorCode)).run {
								resourceStatus.postValue(this)
							}
						}
						BaseModel.ERROR -> {
							retry = {
								loadAfter(params, callback)
							}
							mapOf(Pair(Status.ERROR, it.errorCode)).run {
								resourceStatus.postValue(this)
							}
						}
						else -> {
							mapOf(Pair(Status.LOADING, it.errorCode)).run {
								resourceStatus.postValue(this)
							}
						}
					}
				}
		}
	}
	
	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {
	}
	
	override fun invalidate() {
		super.invalidate()
		scope.cancel()
	}
}
