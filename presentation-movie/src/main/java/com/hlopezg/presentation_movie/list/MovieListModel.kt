package com.hlopezg.presentation_movie.list

import com.hlopezg.presentation_movie.single.MovieModel

data class MovieListModel(
    val page: Int,
    val items: List<MovieModel> = listOf()
)