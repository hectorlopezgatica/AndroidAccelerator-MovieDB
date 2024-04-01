package com.hlopezg.data_local.injection

import android.content.Context
import androidx.room.Room
import com.hlopezg.data_local.AppDatabase
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.tv.TvDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PersistanceModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "my-database",
        ).build()
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

    @Provides
    fun providesTvDao(appDatabase: AppDatabase): TvDao = appDatabase.tvDao()
}