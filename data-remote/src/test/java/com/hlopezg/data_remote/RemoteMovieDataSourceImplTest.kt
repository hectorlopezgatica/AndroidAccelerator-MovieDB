package com.hlopezg.data_remote

import com.hlopezg.data_remote.networking.movie.DiscoverMovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.movie.MovieService
import com.hlopezg.data_remote.source.RemoteMovieDataSourceImpl
import com.hlopezg.domain.DomainTestUtilities
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class RemoteMovieDataSourceImplTest {
    private val movieService = mock<MovieService>()
    private val movieDataSource = RemoteMovieDataSourceImpl(movieService)
    @ExperimentalCoroutinesApi
    @Test
    fun testGetMoviesApiModel() = runTest {
        val remotePosts = LocalDataTestUtilities().getMoviesApiModel()
        val expectedPosts = DomainTestUtilities().getMovies()
        val discoverMovieApiModel = DiscoverMovieApiModel(1, remotePosts, 1, 2)
        whenever(movieService.getDiscoverMovies()).thenReturn(discoverMovieApiModel)
        val result = movieDataSource.getMovies().first()
        Assert.assertEquals(expectedPosts, result)
    }

}