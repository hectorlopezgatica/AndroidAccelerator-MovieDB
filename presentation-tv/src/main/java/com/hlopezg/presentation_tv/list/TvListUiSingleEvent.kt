package com.hlopezg.presentation_tv.list

import com.hlopezg.presentation_common.state.UiSingleEvent

sealed class TvListUiSingleEvent : UiSingleEvent {
    data class OpenTvScreen(val navRoute: String) : TvListUiSingleEvent()
}