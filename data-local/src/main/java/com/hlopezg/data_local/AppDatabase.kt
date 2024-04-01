package com.hlopezg.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.movie.MovieEntity
import com.hlopezg.data_local.tv.TvDao
import com.hlopezg.data_local.tv.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
}