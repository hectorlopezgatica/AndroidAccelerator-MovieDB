package com.hlopezg.data_local.tv

import androidx.room.Embedded
import androidx.room.Relation
import com.hlopezg.data_local.genre.GenreEntity

data class TvWithGenres (
    @Embedded val tvEntity: TvEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "genreId"
    )
    val genres: List<GenreEntity>
)