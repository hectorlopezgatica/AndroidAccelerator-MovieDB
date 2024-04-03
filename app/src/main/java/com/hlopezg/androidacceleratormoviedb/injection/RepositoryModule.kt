package com.hlopezg.androidacceleratormoviedb.injection

import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import com.hlopezg.data_repository.repository.MovieRepositoryImpl
import com.hlopezg.data_repository.repository.TvRepositoryImpl
import com.hlopezg.domain.repository.MovieRepository
import com.hlopezg.domain.repository.TvRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMovieRepository(
        remoteMovieDataSource: RemoteMovieDataSource,
        localMovieDataSource: LocalMovieDataSource,
    ): MovieRepository = MovieRepositoryImpl(
        remoteMovieDataSource = remoteMovieDataSource,
        localMovieDataSource = localMovieDataSource
    )

    @Provides
    fun provideTvRepository(
        remoteTvDataSource: RemoteTvDataSource,
        localTvDataSource: LocalTvDataSource,
    ): TvRepository = TvRepositoryImpl(
        remoteTvDataSource = remoteTvDataSource,
        localTvDataSource = localTvDataSource
    )
}