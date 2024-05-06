package com.hlopezg.domain.repository

import com.hlopezg.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieList(): Flow<List<Movie>>
    fun getMovie(id: Long): Flow<Movie>
    fun saveMovies(movies: List<Movie>): Flow<List<Movie>>
}