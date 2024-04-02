package com.hlopezg.presentation_movie.list

data class MovieListModel(
    val page: Int,
    val items: List<MovieListItemModel> = listOf()
)