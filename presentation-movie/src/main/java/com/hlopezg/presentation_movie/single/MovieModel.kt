package com.hlopezg.presentation_movie.single

import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.models.GenreModel

data class MovieModel(
    override val id: Long,
    val adult: Boolean,
    val genreIds: List<GenreModel>,
    val originalLanguage: String,
    val originalTitle: String,
    override val overview: String,
    val popularity: Double,
    override val posterPath: String,
    val releaseDate: String,
    override val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
): CommonContentDetail {

    override fun getUserScore(): Int{
        val percentage = (voteAverage * 10).toInt()
        return percentage
    }
}

