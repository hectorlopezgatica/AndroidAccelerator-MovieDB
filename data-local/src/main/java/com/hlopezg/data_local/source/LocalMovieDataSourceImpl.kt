package com.hlopezg.data_local.source

import com.hlopezg.data_local.movie.GenreEntity
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.movie.MovieEntity
import com.hlopezg.data_local.movie.MovieWithGenres
import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.domain.entity.Genre
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

fun MovieWithGenres.toMovie() =
    Movie(
        id = this.movieEntity.movieId,
        adult = this.movieEntity.adult,
        backdropPath = this.movieEntity.backdropPath,
        genreIds = this.genres.map { it.toGenre() },
        originalTitle = this.movieEntity.originalTitle,
        originalLanguage = this.movieEntity.originalLanguage,
        overview = this.movieEntity.overview,
        posterPath = this.movieEntity.posterPath,
        popularity = this.movieEntity.popularity,
        releaseDate = this.movieEntity.releaseDate,
        title = this.movieEntity.title,
        voteCount = this.movieEntity.voteCount,
        video = this.movieEntity.video,
        voteAverage = this.movieEntity.voteAverage,
    )

/*fun MovieEntity.toMovie() =
    Movie(
        id = movieId,
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds.map { it.toGenre() },
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage,
    )*/

fun Movie.toMovieEntity() =
    MovieEntity(
        movieId = id,
        adult = adult,
        backdropPath = backdropPath,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = posterPath,
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage,
    )

fun GenreEntity.toGenre() =
    Genre(
        id = genreId,
        name = name,
    )

fun Genre.toGenreEntity(movieId: Long) =
    GenreEntity(
        genreId = id,
        name = name,
        movieId = movieId,
    )