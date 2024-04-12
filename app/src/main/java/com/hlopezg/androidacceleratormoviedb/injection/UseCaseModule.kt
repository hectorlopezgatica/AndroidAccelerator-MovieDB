package com.hlopezg.androidacceleratormoviedb.injection

import com.hlopezg.domain.repository.MovieRepository
import com.hlopezg.domain.repository.TvRepository
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.domain.usecase.GetMoviesUseCase
import com.hlopezg.domain.usecase.GetTvUseCase
import com.hlopezg.domain.usecase.GetTvsUseCase
import com.hlopezg.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetMoviesUseCase(
        configuration: UseCase.Configuration,
        movieRepository: MovieRepository,
    ): GetMoviesUseCase = GetMoviesUseCase(
        configuration = configuration,
        movieRepository = movieRepository
    )

    @Provides
    fun provideGetMovieUseCase(
        configuration: UseCase.Configuration,
        movieRepository: MovieRepository,
    ): GetMovieUseCase = GetMovieUseCase(
        configuration,
        movieRepository
    )

    @Provides
    fun providesGetTvsUseCase(
        configuration: UseCase.Configuration,
        tvRepository: TvRepository,
    ): GetTvsUseCase = GetTvsUseCase(
        configuration,
        tvRepository
    )

    @Provides
    fun providesGetTvUseCase(
        configuration: UseCase.Configuration,
        tvRepository: TvRepository,
    ): GetTvUseCase = GetTvUseCase(
        configuration,
        tvRepository
    )
}