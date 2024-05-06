package com.hlopezg.presentation_movie.list

import com.hlopezg.presentation_common.state.UiAction
import com.hlopezg.presentation_movie.single.MovieModel

sealed class MovieListUiAction : UiAction {
    data object Load : MovieListUiAction()
    data class SingleMovieClick(val movie: MovieModel) : MovieListUiAction()
}