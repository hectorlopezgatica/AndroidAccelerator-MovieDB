package com.hlopezg.presentation_tv.list

import com.hlopezg.presentation_common.state.UiSingleEvent
import com.hlopezg.presentation_tv.single.TvModel

sealed class TvListUiSingleEvent : UiSingleEvent {
    data class OpenTvScreen(val tv: TvModel) : TvListUiSingleEvent()
}