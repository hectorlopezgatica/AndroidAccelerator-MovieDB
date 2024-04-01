package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(private val postService: MovieService) :
    RemoteMovieDataSource {

    override fun getMovies(): Flow<List<Movie>> = flow {
        emit(postService.getMovies())
    }.map { moviesApiModel ->
        moviesApiModel.map { movieApiModel ->
            convert(movieApiModel)
        }
    }.catch {
        throw UseCaseException.PostException(it)
    }

    override fun getMovie(id: Long): Flow<Movie> = flow {
        emit(postService.getMovie(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.PostException(it)
    }

    private fun convert(movieApiModel: MovieApiModel) =
        Movie(
            id = movieApiModel.id,
            adult = movieApiModel.adult,
            backdropPath = movieApiModel.backdropPath,
            genreIds = movieApiModel.genreIds,
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