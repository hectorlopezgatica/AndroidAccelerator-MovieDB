package com.hlopezg.data_remote.source

import com.hlopezg.data_remote.networking.tv.TvApiModel
import com.hlopezg.data_remote.networking.tv.TvDetailApiModel
import com.hlopezg.data_remote.networking.tv.TvService
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import com.hlopezg.domain.entity.Genre
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
        emit(tvService.discoverTvs().results)
    }.map { moviesApiModel ->
        moviesApiModel.map { movieApiModel ->
            convert(movieApiModel)
        }
    }.catch {
        throw UseCaseException.TvException(it)
    }

    override fun getTv(id: Long): Flow<Tv> = flow {
        emit(tvService.getMovie(id))
    }.map {
        convert(it)
    }.catch {
        throw UseCaseException.TvException(it)
    }

    private fun convert(tvApiModel: TvApiModel) =
        Tv(
            id = tvApiModel.id,
            adult = tvApiModel.adult,
            genreIds = tvApiModel.genreIds.map { Genre(it, "") },
            originalLanguage = tvApiModel.originalLanguage,
            originalName = tvApiModel.originalName,
            overview = tvApiModel.overview,
            popularity = tvApiModel.popularity,
            posterPath = tvApiModel.posterPath,
            releaseDate = tvApiModel.firstAirDate,
            title = tvApiModel.name,
            voteAverage = tvApiModel.voteAverage,
            voteCount = tvApiModel.voteCount,
        )

    private fun convert(tvApiModel: TvDetailApiModel) =
        Tv(
            id = tvApiModel.id,
            adult = tvApiModel.adult,
            genreIds = tvApiModel.genreIds.map { Genre(it.id, it.name) },
            originalLanguage = tvApiModel.originalLanguage,
            originalName = tvApiModel.originalName,
            overview = tvApiModel.overview,
            popularity = tvApiModel.popularity,
            posterPath = tvApiModel.posterPath,
            releaseDate = tvApiModel.firstAirDate,
            title = tvApiModel.name,
            voteAverage = tvApiModel.voteAverage,
            voteCount = tvApiModel.voteCount,
        )
}