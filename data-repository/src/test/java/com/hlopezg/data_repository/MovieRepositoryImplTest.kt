package com.hlopezg.data_repository

import com.hlopezg.data_repository.data_source.local.LocalMovieDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteMovieDataSource
import com.hlopezg.data_repository.repository.MovieRepositoryImpl
import com.hlopezg.domain.DomainTestUtilities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieRepositoryImplTest {
    private val localMovieDataSource = mock<LocalMovieDataSource>()
    private val remoteMovieDataSource = mock<RemoteMovieDataSource>()
    private val movieRepositoryImpl = MovieRepositoryImpl(
        localMovieDataSource = localMovieDataSource,
        remoteMovieDataSource = remoteMovieDataSource
    )

    @Test
    fun testGetMovies() = runTest {
        val movies = DomainTestUtilities.getFakeRepository().getMovies()
        whenever( movieRepositoryImpl.getMovieList()).thenReturn(flowOf(movies))
       val expectedMovies = movieRepositoryImpl.getMovieList().first()
        Assert.assertEquals(movies, expectedMovies)
    }

    @Test
    fun testGetMovie() = runTest {
        val movie = DomainTestUtilities.getFakeRepository().getMovie()
        whenever( movieRepositoryImpl.getMovie(1L)).thenReturn(flowOf(movie))
        val expectedMovies = movieRepositoryImpl.getMovie(1L).first()
        Assert.assertEquals(movie, expectedMovies)
    }

    @Test
    fun saveMovies() = runTest {
        val movies = DomainTestUtilities.getFakeRepository().getMovies()
        whenever( localMovieDataSource.getMovies()).thenReturn(flowOf(movies))
        val expectedMovies = movieRepositoryImpl.saveMovies(movies).first()
        verify(localMovieDataSource).saveMovies(movies)
        Assert.assertEquals(movies, expectedMovies)
    }
}