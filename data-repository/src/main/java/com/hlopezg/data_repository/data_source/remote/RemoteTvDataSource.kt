package com.hlopezg.data_repository.data_source.remote

import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow

interface RemoteTvDataSource {
    fun getTvs(): Flow<List<Tv>>
    fun getTv(id: Long): Flow<Tv>
}