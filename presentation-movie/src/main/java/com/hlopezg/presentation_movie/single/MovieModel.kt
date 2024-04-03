package com.hlopezg.presentation_movie.single

data class MovieModel(
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
    val voteAverage: Int,
    val voteCount: Int,
)

data class GenreModel(
    val id: Int,
    val name: String,
)