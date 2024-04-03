package com.hlopezg.data_remote.source

import android.util.Log
import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.awaitResponse
import javax.inject.Inject

class RemoteMovieDataSourceImpl @Inject constructor(private val postService: MovieService) :
    RemoteMovieDataSource {

    override fun getMovies(): Flow<List<Movie>> = flow {
/*        val call = postService.getDiscoverMoviesResponse()
        val response = call.awaitResponse().raw()
        Log.i("ads", response.toString())*/
        emit(postService.getDiscoverMovies().results)
    }.map { moviesApiModel ->
        moviesApiModel.map { movieApiModel ->
            convert(movieApiModel)
        }
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    override fun getMovie(id: Long): Flow<Movie> = flow {
        emit(postService.getMovie(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.MovieException(it)
    }

    private fun convert(movieApiModel: MovieApiModel) =
        Movie(
            id = movieApiModel.id,
            adult = movieApiModel.adult,
            backdropPath = movieApiModel.backdropPath,
            genreIds = movieApiModel.genreIds.map { Genre(it, "") },
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