package com.hlopezg.data_local


import com.hlopezg.data_local.mapper.toTv
import com.hlopezg.data_local.mapper.toTvEntity
import com.hlopezg.data_local.source.LocalTvDataSourceImpl
import com.hlopezg.data_local.tv.TvDao
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

class LocalTvDataSourceImplTest {
    private val tvDao = mock<TvDao>()
    private val tvDataSource = LocalTvDataSourceImpl(tvDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetTvs() = runTest {
        val localTvs = DataTestUtilities.getFakeRepository().getTvsWithGenres()
        val expectedTvs = DataTestUtilities.getFakeRepository().getTvsWithGenres().map { it.toTv() }
        whenever(tvDao.getTvs()).thenReturn(flowOf(localTvs))
        val result = tvDataSource.getTvs().first()
        Assert.assertEquals(expectedTvs, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddUsers() = runTest {
        val localTvs = TestUtilities.getFakeRepository().getTvs().map { it.toTvEntity() }
        val tvs = TestUtilities.getFakeRepository().getTvs()
        tvDataSource.saveTv(tvs)
        verify(tvDao).insertTvs(localTvs)
    }
}