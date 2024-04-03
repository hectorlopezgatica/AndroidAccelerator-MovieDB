package com.hlopezg.data_local.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Transaction
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<MovieWithGenres>>

    @Transaction
    @Query("SELECT * FROM movie WHERE :id = movieId")
    fun getMovie(id: Long): Flow<MovieWithGenres>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)
}