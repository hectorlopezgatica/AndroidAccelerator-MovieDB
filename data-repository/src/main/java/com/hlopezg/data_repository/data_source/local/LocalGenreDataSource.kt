package com.hlopezg.data_repository.data_source.local

import com.hlopezg.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface LocalGenreDataSource {
    fun getGenres(): Flow<List<Genre>>
    fun getGenre(id: Long): Flow<Genre>
    fun getGenresByMovieId(moveId: Long): Flow<List<Genre>>
    suspend fun save(genres: List<Genre>, movieId: Long)
}