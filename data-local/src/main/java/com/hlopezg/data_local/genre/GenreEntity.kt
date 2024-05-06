package com.hlopezg.data_local.genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(
    @PrimaryKey val genreId: Int,
    val name: String,
    val movieId: Long,
)
