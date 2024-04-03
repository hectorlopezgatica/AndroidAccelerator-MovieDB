package com.hlopezg.data_local.injection

import com.hlopezg.data_local.source.LocalMovieDataSourceImpl
import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun binMovieDataSource(localMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource
}