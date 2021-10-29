package io.jcal.theMovie.presentation.ui.home.viewmodel.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.jcal.movies_provider.domain.interactor.UseCasePopularTvShows
import io.jcal.movies_provider.domain.interactor.base.Status
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ShowsDataSource(
	private val scope: CoroutineScope,
	private val useCase: UseCasePopularTvShows,
	private val mapper: PresentationDataMapper
) : PageKeyedDataSource<Int, TvShowUIModel>() {
	
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
		callback: LoadInitialCallback<Int, TvShowUIModel>
	) {
		scope.launch {
			useCase.execute().collect {
				when (it.state) {
					BaseModel.SUCCESS -> {
						val shows = mapper.convert(it)
						callback.onResult(
							shows.results,
							shows.page,
							shows.totalPages,
							null,
							shows.page.inc()
						)
						mapOf(Pair(Status.SUCCESS, shows.messageId)).run {
							resourceStatus.postValue(this)
							initialLoad.postValue(this)
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
	
	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowUIModel>) {
		scope.launch {
			useCase.execute(params = UseCasePopularTvShows.Params(params.key)).collect {
				when (it.state) {
					BaseModel.SUCCESS -> {
						val shows = mapper.convert(it)
						callback.onResult(shows.results, shows.page.inc())
						mapOf(Pair(Status.SUCCESS, mapper.getErrorMessage(it.errorCode))).run {
							resourceStatus.postValue(this)
						}
					}
					BaseModel.ERROR -> {
						retry = {
							loadAfter(params, callback)
						}
						mapOf(Pair(Status.ERROR, mapper.getErrorMessage(it.errorCode))).run {
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
	
	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowUIModel>) {
	}
	
	override fun invalidate() {
		super.invalidate()
		scope.cancel()
	}
}
