package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieUseCase(
    configuration: Configuration,
    private val movieRepository: MovieRepository,
) : UseCase<GetMovieUseCase.Request, GetMovieUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        movieRepository.getMovie(request.movieId)
            .map {
                Response(it)
            }

    data class Request(val movieId: Long) : UseCase.Request
    data class Response(val movie: Movie) : UseCase.Response
}