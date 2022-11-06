package io.jcal.provider.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.jcal.provider.domain.interactor.GetPopularMovieList
import io.jcal.provider.domain.interactor.InternalGetPopularMovies
import io.jcal.provider.repository.MDBRepository

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
	
	@Provides
	fun provideGetPopularMovies(repository: MDBRepository): GetPopularMovieList.UseCase = InternalGetPopularMovies(repository)
}