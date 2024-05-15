package com.hlopezg.data_repository.repository

import com.hlopezg.data_repository.data_source.local.LocalTvDataSource
import com.hlopezg.data_repository.data_source.remote.RemoteTvDataSource
import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TvRepositoryImpl(
    private val localTvDataSource: LocalTvDataSource,
    private val remoteTvDataSource: RemoteTvDataSource,
): TvRepository {
    override fun getTvList(): Flow<List<Tv>> = remoteTvDataSource.getTvs()
    override fun getTv(id: Long): Flow<Tv> = remoteTvDataSource.getTv(id)

    override fun saveTv(tvs: List<Tv>): Flow<List<Tv>> = flow {
        localTvDataSource.saveTv(tvs)
        this.emit(tvs)
    }
}