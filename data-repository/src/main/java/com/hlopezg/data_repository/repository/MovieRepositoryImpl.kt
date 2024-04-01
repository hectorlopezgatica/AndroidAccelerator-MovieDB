package com.hlopezg.data_repository.repository

import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class MovieRepositoryImpl(
    private val localMovieDataSource: LocalMovieDataSource,
    private val remoteMovieDataSource: RemoteMovieDataSource,
): MovieRepository {
    override fun getMovieList(): Flow<List<Movie>> = remoteMovieDataSource.getMovies()
        .onEach {
            localMovieDataSource.saveMovies(it)
        }
    override fun getMovie(id: Long): Flow<Movie> = remoteMovieDataSource.getMovie(id).onEach {
        localMovieDataSource.saveMovies(listOf(it))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun saveMovie(movie: Movie):Flow<Movie> = flow {
        localMovieDataSource.saveMovies(listOf(movie))
        this.emit(Unit)
    }.flatMapLatest {
        getMovie(movie.id)
    }
}