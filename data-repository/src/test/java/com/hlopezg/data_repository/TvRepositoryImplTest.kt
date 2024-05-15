package com.hlopezg.data_repository

import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import com.hlopezg.data_repository.repository.TvRepositoryImpl
import com.hlopezg.domain.DomainTestUtilities
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TvRepositoryImplTest {
    private val localTvDataSource = mock<LocalTvDataSource>()
    private val remoteTvDataSource = mock<RemoteTvDataSource>()
    private val tvRepositoryImpl = TvRepositoryImpl(
        localTvDataSource = localTvDataSource,
        remoteTvDataSource = remoteTvDataSource
    )

    @Test
    fun testGetTvs() = runTest {
        val tvs = DomainTestUtilities.getFakeRepository().getTvs()
        whenever( tvRepositoryImpl.getTvList()).thenReturn(flowOf(tvs))
       val expectedMovies = tvRepositoryImpl.getTvList().first()
        Assert.assertEquals(tvs, expectedMovies)
    }

    @Test
    fun testGetTv() = runTest {
        val tv = DomainTestUtilities.getFakeRepository().getTv()
        whenever( tvRepositoryImpl.getTv(1L)).thenReturn(flowOf(tv))
        val expectedMovies = tvRepositoryImpl.getTv(1L).first()
        Assert.assertEquals(tv, expectedMovies)
    }

    @Test
    fun saveMovies() = runTest {
        val tvs = DomainTestUtilities.getFakeRepository().getTvs()
        whenever( localTvDataSource.getTvs()).thenReturn(flowOf(tvs))
        val expectedMovies = tvRepositoryImpl.saveTv(tvs).first()
        verify(localTvDataSource).saveTv(tvs)
        Assert.assertEquals(tvs, expectedMovies)
    }
}