package com.hlopezg.domain.repository

import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTvList(): Flow<List<Tv>>
    fun getTv(id: Long): Flow<Tv>
    fun saveTv(tv: Tv): Flow<Tv>
}