package io.jcal.theMovie.presentation.ui.home.viewmodel.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import io.jcal.movies_provider.domain.interactor.UseCaseGetPopularMovies
import io.jcal.movies_provider.domain.interactor.UseCasePopularTvShows
import io.jcal.movies_provider.domain.interactor.base.Status
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import io.jcal.theMovie.presentation.mapper.model.TvShowUIModel
import kotlinx.coroutines.CoroutineScope

class MoviesDataSourceFactory(
    private val scope: CoroutineScope,
    private val useCase: UseCaseGetPopularMovies,
    private val mapper: PresentationDataMapper
) :
	DataSource.Factory<Int, MovieUIModel>() {
	
	private var currentSource: MoviesDataSource? = null
	
	private val currentDataSource: MutableLiveData<MoviesDataSource> = MutableLiveData()
	
	private fun createNewDataSource(): MoviesDataSource = MoviesDataSource(scope, useCase, mapper)
	
	val dataSource: LiveData<MoviesDataSource> = currentDataSource
	
	fun invalidateDataSource() {
		currentSource?.invalidate()
	}
	
	fun retryAllField() {
		currentSource?.retryAllFailed()
	}
	
	fun clear() {
		currentSource?.clearCoroutineJobs()
	}
	
	override fun create(): DataSource<Int, MovieUIModel> =
		createNewDataSource().apply {
			currentSource = this
			currentDataSource.postValue(this)
		}
}

class ShowsDataSourceFactory(
    private val scope: CoroutineScope,
    private val useCase: UseCasePopularTvShows,
    private val mapper: PresentationDataMapper
) : DataSource.Factory<Int, TvShowUIModel>() {
	
	private var currentSource: ShowsDataSource? = null
	
	private val currentDataSource: MutableLiveData<ShowsDataSource> = MutableLiveData()
	
	private fun createNewDataSource(): ShowsDataSource = ShowsDataSource(scope, useCase, mapper)
	
	val dataSource: LiveData<ShowsDataSource> = currentDataSource
	
	fun invalidateDataSource() {
		currentSource?.invalidate()
	}
	
	fun retryAllField() {
		currentSource?.retryAllFailed()
	}
	
	fun clear() {
		currentSource?.clearCoroutineJobs()
	}
	
	override fun create(): DataSource<Int, TvShowUIModel> =
		createNewDataSource().apply {
			currentSource = this
			currentDataSource.postValue(this)
		}
}

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Listing<T>(
	// the LiveData of paged lists for the UI to observe
    val pagedList: LiveData<PagedList<T>>,
	// represents the network request status to show to the user
    val resourceState: LiveData<Map<Status, Int>>,
	// represents the refresh status to show to the user. Separate from networkState, this
	// value is importantly only when refresh is requested.
    val refreshState: LiveData<Map<Status, Int>>,
	// refreshes the whole data and fetches it from scratch.
    val refresh: () -> Unit,
	// retries any failed requests.
    val retry: () -> Unit,
	// the way to stop jobs from running since no lifecycle provided
    val clearCoroutineJobs: () -> Unit
)
