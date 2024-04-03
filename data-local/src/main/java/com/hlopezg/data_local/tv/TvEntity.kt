package com.hlopezg.data_local.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv")
data class TvEntity(
    val adult: Boolean,
    @ColumnInfo("backdrop_path") val backdropPath: String,
    @PrimaryKey val id: Int,
    @ColumnInfo("original_language") val originalLanguage: String,
    @ColumnInfo("original_title") val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @ColumnInfo("vote_average") val voteAverage: Double,
    val voteCount: Int
)
