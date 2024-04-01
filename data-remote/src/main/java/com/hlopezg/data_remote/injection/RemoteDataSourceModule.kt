package com.hlopezg.data_remote.injection

import com.hlopezg.data_remote.source.RemoteMovieDataSourceImpl
import com.hlopezg.data_remote.source.RemoteTvDataSourceImpl
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule{
    @Binds
    abstract fun bindsMovieDataSource(movieDataSourceImpl: RemoteMovieDataSourceImpl): RemoteMovieDataSource

    @Binds
    abstract fun bindsTvDataSource(tvDataSourceImpl: RemoteTvDataSourceImpl): RemoteTvDataSource
}
