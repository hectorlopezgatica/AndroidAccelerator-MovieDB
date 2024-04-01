package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_remote.networking.tv.TvApiModel
import com.hlopezg.data_remote.networking.tv.TvService
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.entity.UseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteTvDataSourceImpl  @Inject constructor(private val tvService: TvService) :
    RemoteTvDataSource {

    override fun getTvs(): Flow<List<Tv>> = flow {
        emit(tvService.getMovies())
    }.map { moviesApiModel ->
        moviesApiModel.map { movieApiModel ->
            convert(movieApiModel)
        }
    }.catch {
        throw UseCaseException.PostException(it)
    }

    override fun getTv(id: Long): Flow<Tv> = flow {
        emit(tvService.getMovie(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.PostException(it)
    }

    private fun convert(tvApiModel: TvApiModel) =
        Tv(
            id = tvApiModel.id,
            adult = tvApiModel.adult,
            backdropPath = tvApiModel.backdropPath,
            genreIds = tvApiModel.genreIds,
            originalLanguage = tvApiModel.originalLanguage,
            originalTitle = tvApiModel.originalTitle,
            overview = tvApiModel.overview,
            popularity = tvApiModel.popularity,
            posterPath = tvApiModel.posterPath,
            releaseDate = tvApiModel.releaseDate,
            title = tvApiModel.title,
            video = tvApiModel.video,
            voteAverage = tvApiModel.voteAverage,
            voteCount = tvApiModel.voteCount,
        )
}