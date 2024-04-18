package com.hlopezg.presentation_tv.single

import com.hlopezg.presentation_common.models.GenreModel

class TvModel(
    val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<GenreModel>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
){
    fun getUserScore(): Int{
        val percentage = (voteAverage * 10).toInt()
        return percentage
    }
}