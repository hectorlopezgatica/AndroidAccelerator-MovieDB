package com.hlopezg.domain.usecase

import com.hlopezg.domain.TestUtilities
import com.hlopezg.domain.repository.TvRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetTvUseCaseTest {
    private val tvRepository: TvRepository = mock<TvRepository>()
    private val useCase = GetTvUseCase(
        mock(),
        tvRepository
    )

    @Test
    fun testProcess() = runTest {
        val request = GetTvUseCase.Request(0L)
        val tv = TestUtilities.getFakeRepository().getTv()
        whenever(tvRepository.getTv(request.tvId)).thenReturn(flowOf(tv))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetTvUseCase.Response(tv), response)
    }
}