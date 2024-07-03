package com.hlopezg.presentation.content

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiSingleEvent
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CommonContentDetailViewModel @Inject constructor(
    private val generativeModel: GenerativeModel
) : MviViewModel<CommonContentDetailModel, UiState<CommonContentDetailModel>, CommonContentDetailUiAction, UiSingleEvent>() {
    override fun initState(): UiState<CommonContentDetailModel> =
        UiState.Loading(LoadingType.NoLoading)

    override fun handleAction(action: CommonContentDetailUiAction) {
        when (action) {
            is CommonContentDetailUiAction.ShowSuggestWhereToWatch -> {
                generateWhereToWatchThisMovie(action.title)
            }

            is CommonContentDetailUiAction.ShowReview -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val url = URL(action.posterImage)
                    val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    generateReview(action.title,image)
                }
            }

            is CommonContentDetailUiAction.ShowDetail -> {
                submitState(UiState.Success(CommonContentDetailModel("")))
            }
        }
    }

    private fun generateWhereToWatchThisMovie(title: String) {
        viewModelScope.launch {
            submitState(UiState.Loading(LoadingType.ContentDetail))
            val response =
                generativeModel.generateContent(title)
            response.text?.let {
                submitState(UiState.Success(CommonContentDetailModel(it)))
            }
        }
    }

    private fun generateReview(title: String, imagePoster: Bitmap) {
        viewModelScope.launch {
            submitState(UiState.Loading(LoadingType.ContentDetail))
            val response = generativeModel.generateContent(content {
                text(title)
                text("Puedes hacer una review de esta película?")
                image(imagePoster)
            }
            )

            response.text?.let {
                submitState(UiState.Success(CommonContentDetailModel(it)))
            }
        }
    }
}