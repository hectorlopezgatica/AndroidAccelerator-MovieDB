package com.hlopezg.domain.repository

import com.hlopezg.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun getGenreList(): Flow<List<Genre>>
    fun getGenre(id: Long): Flow<Genre>
    //fun saveGenres(genres: List<Genre>): Flow<List<Genre>>
}