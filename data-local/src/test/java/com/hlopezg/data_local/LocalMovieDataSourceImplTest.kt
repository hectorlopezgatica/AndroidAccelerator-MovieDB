package com.hlopezg.data_local

import com.hlopezg.data_local.mapper.toMovie
import com.hlopezg.data_local.mapper.toMovieEntity
import com.hlopezg.data_local.movie.MovieDao
import com.hlopezg.data_local.source.LocalMovieDataSourceImpl
import com.hlopezg.domain.TestUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class LocalMovieDataSourceImplTest {
    private val movieDao = mock<MovieDao>()
    private val movieDataSource = LocalMovieDataSourceImpl(movieDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetMovies() = runTest {
        val localPosts = DataTestUtilities.getFakeRepository().getMoviesWithGenres()
        val expectedPosts = DataTestUtilities.getFakeRepository().getMoviesWithGenres().map { it.toMovie() }
        whenever(movieDao.getMovies()).thenReturn(flowOf(localPosts))
        val result = movieDataSource.getMovies().first()
        Assert.assertEquals(expectedPosts, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddUsers() = runTest {
        val localPosts = TestUtilities.getFakeRepository().getMovies().map { it.toMovieEntity() }
        val posts = TestUtilities.getFakeRepository().getMovies()
        movieDataSource.saveMovies(posts)
        verify(movieDao).insertMovies(localPosts)
    }
}