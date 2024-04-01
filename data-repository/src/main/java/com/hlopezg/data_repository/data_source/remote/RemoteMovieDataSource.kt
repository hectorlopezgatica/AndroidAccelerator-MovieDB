package com.hlopezg.data_repository.data_source.remote

import com.hlopezg.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface RemoteMovieDataSource {
    fun getMovies(): Flow<List<Movie>>
    fun getMovie(id: Long): Flow<Movie>
}