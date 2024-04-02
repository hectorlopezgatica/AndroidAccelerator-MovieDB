package com.hlopezg.presentation_movie.single

import com.hlopezg.presentation_common.state.UiAction

sealed class MovieUiAction: UiAction {
    data class Load(val movieId: Long): MovieUiAction()
}