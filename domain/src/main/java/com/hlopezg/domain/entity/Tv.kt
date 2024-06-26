package com.hlopezg.domain.entity


data class Tv(
    val adult: Boolean,
    val genreIds: List<Genre>,
    val id: Long,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)