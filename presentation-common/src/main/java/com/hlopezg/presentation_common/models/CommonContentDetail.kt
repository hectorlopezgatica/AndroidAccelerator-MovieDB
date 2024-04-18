package com.hlopezg.presentation_common.models

interface CommonContentDetail {
    val title: String
    val posterPath: String
    val overview: String
    val genreIds: List<GenreModel>
    fun getUserScore(): Int
}