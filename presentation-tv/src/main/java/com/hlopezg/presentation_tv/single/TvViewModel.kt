package com.hlopezg.presentation_tv.single

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.GetTvUseCase
import com.hlopezg.presentation_common.state.LoadingType
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiSingleEvent
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val tvUseCase: GetTvUseCase,
    private val tvConverter: TvConverter,
) : MviViewModel<TvModel, UiState<TvModel>, TvUiAction, UiSingleEvent>() {
    override fun initState(): UiState<TvModel> = UiState.Loading(LoadingType.ContentDetail)

    override fun handleAction(action: TvUiAction) {
        when (action) {
            is TvUiAction.Load -> {
                loadTv(action.id)
            }
        }
    }

    private fun loadTv(idTv: Long) {
        viewModelScope.launch {
            tvUseCase.execute(GetTvUseCase.Request(idTv)).map {
                tvConverter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }
}