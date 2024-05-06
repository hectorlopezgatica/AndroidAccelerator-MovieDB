package com.hlopezg.data_local.crossref

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenresCrossRef(
    val movieId: Long,
    val genreId: Long,
)
