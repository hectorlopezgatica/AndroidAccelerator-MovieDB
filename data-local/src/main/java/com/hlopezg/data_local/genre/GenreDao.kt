package com.hlopezg.data_local.genre

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.hlopezg.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {
    @Transaction
    @Query("SELECT * FROM genre")
    fun getGenres(): Flow<List<GenreEntity>>

    @Transaction
    @Query("SELECT * FROM genre WHERE :id = genreId")
    fun getGenre(id: Long): Flow<GenreEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genre: List<GenreEntity>)

    @Transaction
    @Query("SELECT genre.genreId as id, genre.name as name " +
            "FROM genre " +
            "INNER JOIN movies_genres_cross_ref ON genre.genreId = movies_genres_cross_ref.genreId " +
            "WHERE :movieId = movies_genres_cross_ref.movieId")
    fun getGenresByMovieId(movieId: Long): Flow<List<Genre>>
}