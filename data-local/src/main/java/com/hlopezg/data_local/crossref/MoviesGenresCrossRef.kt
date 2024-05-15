package com.hlopezg.data_local.crossref

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"], tableName = "movies_genres_cross_ref")
data class MoviesGenresCrossRef(
    val movieId: Long,
    val genreId: Long,
)
