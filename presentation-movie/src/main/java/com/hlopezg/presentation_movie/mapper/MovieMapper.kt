package com.hlopezg.presentation_movie.mapper

import com.hlopezg.domain.entity.Movie
import com.hlopezg.presentation_common.mapper.toGenreItemModel
import com.hlopezg.presentation_common.utils.Utils
import com.hlopezg.presentation_movie.single.MovieModel

fun Movie.toItemModel() =
    MovieModel(
        id = id,
        adult = adult,
        backdropPath = "${Utils.IMAGE_PATH}$backdropPath",
        genreIds = genreIds.map { it.toGenreItemModel() },
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = "${Utils.IMAGE_PATH}$posterPath",
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage,
    )