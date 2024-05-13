package com.hlopezg.domain.usecase

import com.hlopezg.domain.DomainTestUtilities
import com.hlopezg.domain.repository.TvRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetTvsUseCaseTest {
    private val tvRepository: TvRepository = mock<TvRepository>()
    private val useCase = GetTvsUseCase(
        mock(),
        tvRepository
    )

    @Test
    fun testProcess() = runTest {
        val request = GetTvsUseCase.Request
        val tvs = DomainTestUtilities.getFakeRepository().getTvs()
        whenever(tvRepository.getTvList()).thenReturn(flowOf(tvs))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetTvsUseCase.Response(tvs), response)
    }
}