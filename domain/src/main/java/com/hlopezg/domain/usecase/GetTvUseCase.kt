package com.hlopezg.domain.usecase

import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTvUseCase(
    configuration: Configuration,
    private val repository: TvRepository,
) : UseCase<GetTvUseCase.Request, GetTvUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> =
        repository.getTv(request.tvId).map {
            Response(it)
        }

    data class Request(val tvId: Long) : UseCase.Request
    data class Response(val tv: Tv) : UseCase.Response
}