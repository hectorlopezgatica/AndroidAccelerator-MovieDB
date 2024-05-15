package com.hlopezg.data_local.tv

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Transaction
    @Query("SELECT * FROM tv")
    fun getTvs(): Flow<List<TvWithGenres>>

    @Transaction
    @Query("SELECT * FROM tv WHERE :id = id")
    fun getTv(id: Long): Flow<TvWithGenres>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(tvs: List<TvEntity>)
}