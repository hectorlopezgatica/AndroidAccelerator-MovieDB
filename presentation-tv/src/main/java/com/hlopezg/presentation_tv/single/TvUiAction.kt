package com.hlopezg.presentation_tv.single

import com.hlopezg.presentation_common.state.UiAction

sealed class TvUiAction : UiAction {
    data class Load(val id: Long) : TvUiAction()
}