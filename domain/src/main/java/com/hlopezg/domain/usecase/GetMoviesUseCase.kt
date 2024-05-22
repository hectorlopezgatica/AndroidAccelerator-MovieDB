package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMoviesUseCase(
    configuration: Configuration,
    private val movieRepository: MovieRepository,
) : UseCase<GetMoviesUseCase.Request, GetMoviesUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        movieRepository.getMovieList()
            .map {
                Response(it)
            }

    object Request : UseCase.Request
    data class Response(val movies: List<Movie>) : UseCase.Response
}