package com.hlopezg.domain.usecase

import com.hlopezg.domain.TestUtilities
import com.hlopezg.domain.repository.MovieRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetMoviesUseCaseTest {
    private val movieRepository: MovieRepository = mock<MovieRepository>()
    private val useCase = GetMoviesUseCase(
        mock(),
        movieRepository,
    )

    @Test
    fun testProcess() = runTest {
        val request = GetMoviesUseCase.Request
        val movies = TestUtilities.getFakeRepository().getMovies()
        whenever(movieRepository.getMovieList()).thenReturn(flowOf(movies))
        val response = useCase.process(request).first()
        Assert.assertEquals(GetMoviesUseCase.Response(movies), response)
    }
}