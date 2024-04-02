package com.hlopezg.presentation_movie.list

import com.hlopezg.presentation_common.state.UiSingleEvent

sealed class MovieListUiSingleEvent : UiSingleEvent {
    data class OpenMovieScreen(val navRoute: String) : MovieListUiSingleEvent()
}