package com.hlopezg.data_repository.data_source.local

import com.hlopezg.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface LocalMovieDataSource {
    fun getMovies(): Flow<List<Movie>>
    fun getMovie(movieId: Long): Flow<Movie>
    suspend fun saveMovies(movies: List<Movie>)
}