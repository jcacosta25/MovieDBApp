package io.jcal.theMovie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.jcal.movies_provider.domain.interactor.UseCasePopularMovies
import io.jcal.theMovie.presentation.mapper.PresentationDataMapper
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
    private val useCasePopularMovies: UseCasePopularMovies,
    private val mapper: PresentationDataMapper
) : ViewModel()