package io.jcal.theMovie.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.jcal.movies_provider.domain.interactor.UseCasePopularMoviesFlow
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import kotlinx.coroutines.CoroutineScope

class MoviesDataSourceFactory(
    private val scope: CoroutineScope,
    private val useCasePopularMoviesFlow: UseCasePopularMoviesFlow,
    private val mapper: PresentationDataMapper
) :
    DataSource.Factory<Int, MovieUIModel>() {

    private val moviesFeedData: MutableLiveData<MoviesDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, MovieUIModel> =
        MoviesDataSource(scope, useCasePopularMoviesFlow, mapper).let {
            moviesFeedData.postValue(it)
            it
        }

    fun getMoviesFeed(): LiveData<MoviesDataSource> = moviesFeedData
}