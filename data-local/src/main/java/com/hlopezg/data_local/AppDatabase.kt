package com.hlopezg.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hlopezg.data_local.movie.GenreEntity
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.movie.MovieEntity
import com.hlopezg.data_local.tv.TvDao
import com.hlopezg.data_local.tv.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class, GenreEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}