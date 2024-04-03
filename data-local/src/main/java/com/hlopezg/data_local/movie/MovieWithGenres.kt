package com.hlopezg.data_local.movie

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithGenres(
    @Embedded val movieEntity: MovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId"
    )
    val genres: List<GenreEntity>
)
