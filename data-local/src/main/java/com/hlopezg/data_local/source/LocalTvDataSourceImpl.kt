package com.hlopezg.data_local.source

import com.hlopezg.data_local.mapper.toTv
import com.hlopezg.data_local.tv.TvDao
import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import com.hlopezg.domain.entity.Tv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalTvDataSourceImpl @Inject constructor(
    private val tvDao: TvDao,
): LocalTvDataSource {
    override fun getTvs(): Flow<List<Tv>> = tvDao.getTvs().map { tvListEntity ->
        tvListEntity.map { tvEntity ->
            tvEntity.toTv()
        }
    }

    override fun getTv(id: Long): Flow<Tv> {
        TODO("Not yet implemented")
    }

    override suspend fun saveTv(tv: Tv) {
        TODO("Not yet implemented")
    }


}