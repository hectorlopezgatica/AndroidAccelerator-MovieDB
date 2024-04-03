package com.hlopezg.data_local.tv

import androidx.room.Embedded
import androidx.room.Relation
import com.hlopezg.data_local.movie.GenreEntity
import com.hlopezg.data_local.movie.MovieEntity

data class TvWithGenres (
    @Embedded val tvEntity: TvEntity,
    @Relation(
        parentColumn = "tvId",
        entityColumn = "genreId"
    )
    val genres: List<GenreEntity>
)