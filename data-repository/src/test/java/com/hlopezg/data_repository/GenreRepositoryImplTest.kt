package com.hlopezg.data_repository

import com.hlopezg.data_repository.data_source.local.LocalGenreDataSource
import com.hlopezg.data_repository.repository.GenreRepositoryImpl
import com.hlopezg.domain.DomainTestUtilities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class GenreRepositoryImplTest {
    private val localGenreDataSource = mock<LocalGenreDataSource>()
    //private val remoteGenreDataSource = mock<RemoteGenreDataSource>()
    private val genreRepositoryImpl = GenreRepositoryImpl(
        localGenreDataSource = localGenreDataSource,
    )

    @Test
    fun testGetMovies() = runTest {
        val movies = DomainTestUtilities.getFakeRepository().getGenres()
        whenever( genreRepositoryImpl.getGenreList()).thenReturn(flowOf(movies))
       val expectedMovies = genreRepositoryImpl.getGenreList().first()
        Assert.assertEquals(movies, expectedMovies)
    }

    @Test
    fun testGetMovie() = runTest {
        val movie = DomainTestUtilities.getFakeRepository().getGenre()
        whenever( genreRepositoryImpl.getGenre(1L)).thenReturn(flowOf(movie))
        val expectedMovies = genreRepositoryImpl.getGenre(1L).first()
        Assert.assertEquals(movie, expectedMovies)
    }

/*    @Test
    fun saveMovies() = runTest {
        val movies = TestUtilities.getFakeRepository().getMovies()
        whenever( localGenreDataSource.getMovies()).thenReturn(flowOf(movies))
        val expectedMovies = genreRepositoryImpl.saveMovies(movies).first()
        verify(localGenreDataSource).saveMovies(movies)
        Assert.assertEquals(movies, expectedMovies)
    }*/
}