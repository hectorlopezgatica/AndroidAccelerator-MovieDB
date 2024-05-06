package com.hlopezg.data_local

import com.hlopezg.data_local.genre.GenreDao
import com.hlopezg.data_local.mapper.toGenre
import com.hlopezg.data_local.source.LocalGenreDataSourceImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class LocalGenreDataSourceImplTest {
    private val genreDao = mock<GenreDao>()
    private val genreDataSource = LocalGenreDataSourceImpl(genreDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetGenres() = runTest {
        val localGenres = DataTestUtilities.getFakeRepository().getGenres()
        val expectedGenres = DataTestUtilities.getFakeRepository().getGenres().map { it.toGenre() }
        whenever(genreDao.getGenres()).thenReturn(flowOf(localGenres))
        val result = genreDataSource.getGenres().first()
        Assert.assertEquals(expectedGenres, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddGenres() = runTest {
        val localGenres = DataTestUtilities.getFakeRepository().getGenres()
        val genres = DataTestUtilities.getFakeRepository().getGenres().map { it.toGenre() }
        genreDataSource.save(genres, 1)
        verify(genreDao).insertGenres(localGenres)
    }
}