package com.hlopezg.data_local.injection

import com.hlopezg.data_local.source.LocalGenreDataSourceImpl
import com.hlopezg.data_local.source.LocalMovieDataSourceImpl
import com.hlopezg.data_local.source.LocalTvDataSourceImpl
import com.hlopezg.data_repository.data_source.local.LocalGenreDataSource
import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun binMovieDataSource(localMovieDataSourceImpl: LocalMovieDataSourceImpl): LocalMovieDataSource

    @Binds abstract fun binTvDataSource(localTvDataSource: LocalTvDataSourceImpl): LocalTvDataSource

    @Binds abstract fun binGenresDataSource(localGenreDataSource: LocalGenreDataSourceImpl): LocalGenreDataSource


}