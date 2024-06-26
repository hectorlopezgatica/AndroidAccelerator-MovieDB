package com.hlopezg.presentation_tv.mapper

import com.hlopezg.domain.entity.Tv
import com.hlopezg.presentation_common.mapper.toGenreItemModel
import com.hlopezg.presentation_common.utils.Utils
import com.hlopezg.presentation_tv.single.TvModel

fun Tv.toItemModel() =
    TvModel(
        id = id,
        adult = adult,
        genreIds = genreIds.map { it.toGenreItemModel() },
        originalName = originalName,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = "${Utils.IMAGE_PATH}$posterPath",
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        voteAverage = voteAverage,
    )