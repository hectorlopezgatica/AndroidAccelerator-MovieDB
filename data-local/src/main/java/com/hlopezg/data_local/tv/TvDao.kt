package com.hlopezg.data_local.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query("SELECT * FROM tv")
    fun getTvs(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tv WHERE :id = id")
    fun getTv(id: String): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(movies: List<TvEntity>)
}