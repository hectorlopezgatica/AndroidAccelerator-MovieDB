package com.hlopezg.data_local.crossref

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction

@Dao
interface MovieGenresDao {
    @Transaction
    @Insert
    fun insert(moviesGenres: MoviesGenresCrossRef)
}