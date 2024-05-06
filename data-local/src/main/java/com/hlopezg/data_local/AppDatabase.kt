package com.hlopezg.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hlopezg.data_local.crossref.MovieGenresCrossRef
import com.hlopezg.data_local.crossref.MovieGenresDao
import com.hlopezg.data_local.genre.GenreDao
import com.hlopezg.data_local.genre.GenreEntity
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.movie.MovieEntity
import com.hlopezg.data_local.movie.MovieWithGenres
import com.hlopezg.data_local.tv.TvDao
import com.hlopezg.data_local.tv.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class, GenreEntity::class, MovieGenresCrossRef::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
    abstract fun genreDao(): GenreDao
    abstract fun movieGenresDao(): MovieGenresDao
}