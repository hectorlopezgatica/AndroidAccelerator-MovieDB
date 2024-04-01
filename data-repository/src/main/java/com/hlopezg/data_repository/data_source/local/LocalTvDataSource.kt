package com.hlopezg.data_repository.data_source.local

import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow

interface LocalTvDataSource {
    fun getTv(): Flow<Tv>
    suspend fun saveTv(tv: Tv)
}