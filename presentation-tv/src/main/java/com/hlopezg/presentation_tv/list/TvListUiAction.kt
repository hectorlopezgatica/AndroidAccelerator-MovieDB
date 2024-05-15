package com.hlopezg.presentation_tv.list

import com.hlopezg.presentation_common.state.UiAction
import com.hlopezg.presentation_tv.single.TvModel

sealed class TvListUiAction : UiAction {
    data object Load : TvListUiAction()
    data class SingleMovieClick(val tv: TvModel) : TvListUiAction()
}