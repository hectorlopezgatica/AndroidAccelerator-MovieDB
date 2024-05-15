package com.hlopezg.data_repository.data_source.remote

import com.hlopezg.domain.entity.Genre
import kotlinx.coroutines.flow.Flow

@Deprecated("Not necessary right now, maybe it will be in the future")
interface RemoteGenreDataSource {
    fun getGenres(): Flow<List<Genre>>
    fun getGenre(id: Long): Flow<Genre>
}