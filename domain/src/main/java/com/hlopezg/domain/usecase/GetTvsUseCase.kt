package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvsUseCase (
    configuration: UseCase.Configuration,
    private val tvRepository: TvRepository,
): UseCase<GetTvsUseCase.Request, GetTvsUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        tvRepository.getTvList()
            .map {
                Response(it)
            }

    object Request : UseCase.Request
    data class Response(val movies: List<Tv>) : UseCase.Response
}