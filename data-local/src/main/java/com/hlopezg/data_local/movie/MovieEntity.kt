package com.hlopezg.data_local.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val movieId: Long,
    val adult: Boolean,
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