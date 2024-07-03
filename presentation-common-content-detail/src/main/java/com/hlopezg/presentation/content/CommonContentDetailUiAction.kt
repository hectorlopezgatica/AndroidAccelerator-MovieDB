package com.hlopezg.presentation.content

import android.graphics.Bitmap
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.state.UiAction

sealed class CommonContentDetailUiAction: UiAction {
    data class ShowSuggestWhereToWatch(val title: String): CommonContentDetailUiAction()
    data class ShowReview(val title: String, val posterImage: String): CommonContentDetailUiAction()
    data class ShowDetail(val commonContentDetail: CommonContentDetail): CommonContentDetailUiAction()
}