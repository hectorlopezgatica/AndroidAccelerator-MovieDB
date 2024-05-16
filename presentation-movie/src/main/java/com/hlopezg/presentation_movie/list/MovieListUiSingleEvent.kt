package com.hlopezg.presentation_movie.list

import com.hlopezg.presentation_common.state.UiSingleEvent
import com.hlopezg.presentation_movie.single.MovieModel

sealed class MovieListUiSingleEvent : UiSingleEvent {
    data class OpenMovieScreen(val movie: MovieModel) : MovieListUiSingleEvent()
}