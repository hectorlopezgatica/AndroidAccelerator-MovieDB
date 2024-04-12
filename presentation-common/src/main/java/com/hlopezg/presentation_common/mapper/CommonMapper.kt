package com.hlopezg.presentation_common.mapper

import com.hlopezg.domain.entity.Genre
import com.hlopezg.presentation_common.models.GenreModel

fun Genre.toGenreItemModel() =
    GenreModel(
        id = id,
        name = name,
    )