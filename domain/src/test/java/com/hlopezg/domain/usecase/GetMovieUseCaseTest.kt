package com.hlopezg.domain.usecase

import com.hlopezg.domain.DomainTestUtilities
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetMovieUseCaseTest {
    private val movieRepository: MovieRepository = mock<MovieRepository>()
    private val useCase = GetMovieUseCase(
        mock(),
        movieRepository
    )

    @Test
    fun testProcess() = runTest {
        val request = GetMovieUseCase.Request(0L)
        val movie = DomainTestUtilities.getFakeRepository().getMovie()
        whenever(movieRepository.getMovie(request.movieId)).thenReturn(flowOf(movie))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetMovieUseCase.Response(movie), response)
    }
}