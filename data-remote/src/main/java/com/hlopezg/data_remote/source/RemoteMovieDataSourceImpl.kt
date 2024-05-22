package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieDetailApiModel
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_repository.data_source.local.LocalGenreDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
    private val localGenreDataSource: LocalGenreDataSource,
) :
    RemoteMovieDataSource {

    override fun getMovies(): Flow<List<Movie>> = flow {
        emit(movieService.getDiscoverMovies().results)
    }.map { moviesApiModel ->
        moviesApiModel.map { movieApiModel ->
            val genre = localGenreDataSource.getGenre(movieApiModel.id).first()
            convert(movieApiModel, genre)
        }
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    override fun getMovie(id: Long): Flow<Movie> = flow {
        emit(movieService.getMovie(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    private fun convert(movieApiModel: MovieApiModel, genre: Genre? = null) =
        Movie(
            id = movieApiModel.id,
            adult = movieApiModel.adult,
            genreIds = if (genre != null) listOf(genre) else movieApiModel.genreIds.map {
                Genre(
                    it,
                    ""
                )
            },
            originalLanguage = movieApiModel.originalLanguage,
            originalTitle = movieApiModel.originalTitle,
            overview = movieApiModel.overview,
            popularity = movieApiModel.popularity,
            posterPath = movieApiModel.posterPath,
            releaseDate = movieApiModel.releaseDate,
            title = movieApiModel.title,
            video = movieApiModel.video,
            voteAverage = movieApiModel.voteAverage,
            voteCount = movieApiModel.voteCount,
        )

    private fun convert(movieApiModel: MovieDetailApiModel) =
        Movie(
            id = movieApiModel.id,
            adult = movieApiModel.adult,
            genreIds = movieApiModel.genreIds.map { Genre(it.id, it.name) },
            originalLanguage = movieApiModel.originalLanguage,
            originalTitle = movieApiModel.originalTitle,
            overview = movieApiModel.overview,
            popularity = movieApiModel.popularity,
            posterPath = movieApiModel.posterPath,
            releaseDate = movieApiModel.releaseDate,
            title = movieApiModel.title,
            video = movieApiModel.video,
            voteAverage = movieApiModel.voteAverage,
            voteCount = movieApiModel.voteCount,
        )
}