package com.hlopezg.data_local.source

import com.hlopezg.data_local.mapper.toMovie
import com.hlopezg.data_local.mapper.toMovieEntity
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalMovieDataSourceImpl @Inject constructor(private val movieDao: MovieDao): LocalMovieDataSource {
    override fun getMovies(): Flow<List<Movie>> = movieDao.getMovies().map { moviesEntity ->
        moviesEntity.map {
            it.toMovie()
        }
    }

    override fun getMovie(movieId: Long): Flow<Movie> = movieDao.getMovie(movieId).map {movieEntity ->
        movieEntity.toMovie()
    }

    override suspend fun saveMovies(movies: List<Movie>) = movieDao.insertMovies(movies.map { movie ->
        movie.toMovieEntity()
        /*
        add genre dao
        genreDao.insertGenres(movie.genreIds.map { genre -> genre.toGenreEntity(movie.id) } )
         */
    })
}