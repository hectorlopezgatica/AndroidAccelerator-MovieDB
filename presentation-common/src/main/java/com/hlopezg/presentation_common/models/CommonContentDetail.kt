package com.hlopezg.presentation_common.models

interface CommonContentDetail {
    val id: Long
    val title: String
    val posterPath: String
    val overview: String

    // val genreIds: List<GenreModel>
    fun getUserScore(): Int
}