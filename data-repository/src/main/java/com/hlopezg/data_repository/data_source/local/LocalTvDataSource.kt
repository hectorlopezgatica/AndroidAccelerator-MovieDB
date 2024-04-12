package com.hlopezg.data_repository.data_source.local

import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow

interface LocalTvDataSource {
    fun getTvs(): Flow<List<Tv>>
    fun getTv(id: Long): Flow<Tv>
    suspend fun saveTv(tv: Tv)
}