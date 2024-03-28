package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMovieUseCase(
    configuration: UseCase.Configuration,
    private val movieRepository: MovieRepository,
): UseCase<GetMovieUseCase.Request, GetMovieUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        movieRepository.getMovieList()
            .map {
                Response(it)
            }

    class Request : UseCase.Request
    data class Response(val movies: List<Movie>) : UseCase.Response
}