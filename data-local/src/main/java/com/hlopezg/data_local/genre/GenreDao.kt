package com.hlopezg.data_local.genre

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Transaction
    @Query("SELECT * FROM movie")
    fun getGenres(): Flow<List<GenreEntity>>

    @Transaction
    @Query("SELECT * FROM movie WHERE :id = movieId")
    fun getGenre(id: Long): Flow<GenreEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genre: List<GenreEntity>)
}