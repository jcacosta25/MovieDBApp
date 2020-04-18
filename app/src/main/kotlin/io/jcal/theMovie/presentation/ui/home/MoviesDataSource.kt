package io.jcal.theMovie.presentation.ui.home

import androidx.paging.PageKeyedDataSource
import io.jcal.movies_provider.domain.interactor.UseCasePopularMoviesFlow
import io.jcal.movies_provider.repository.mapper.model.BaseModel
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import io.jcal.theMovie.presentation.mapper.model.MovieUIModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesDataSource(
    private val scope: CoroutineScope,
    private val useCasePopularMoviesFlow: UseCasePopularMoviesFlow,
    private val mapper: PresentationDataMapper
) : PageKeyedDataSource<Int, MovieUIModel>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieUIModel>
    ) {
        scope.launch {
            useCasePopularMoviesFlow.execute(params = UseCasePopularMoviesFlow.Params()).collect {
                //onResult(@NonNull List<Value> data, int position, int totalCount,
                //                 @Nullable Key previousPageKey, @Nullable Key nextPageKey)
                when(it.state) {
                    BaseModel.SUCCESS -> {
                        val movies = mapper.convert(it)
                        callback.onResult(
                            movies.results,
                            movies.page,
                            movies.totalPages,
                            null,
                            movies.page.inc()
                        )
                    }
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {
        scope.launch {
            useCasePopularMoviesFlow.execute(params = UseCasePopularMoviesFlow.Params(params.key))
                .collect {
                    when(it.state) {
                        BaseModel.SUCCESS -> {
                            val movies = mapper.convert(it)
                            callback.onResult(movies.results, movies.page.inc())
                        }
                    }
                }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieUIModel>) {
        scope.launch {
            useCasePopularMoviesFlow.execute(params = UseCasePopularMoviesFlow.Params(params.key))
                .collect {
                    when(it.state) {
                        BaseModel.SUCCESS -> {
                            val movies = mapper.convert(it)
                            callback.onResult(movies.results, movies.page.inc())
                        }
                    }
                }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}