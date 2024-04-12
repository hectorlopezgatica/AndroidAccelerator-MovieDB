package com.hlopezg.presentation_tv.list

import androidx.lifecycle.viewModelScope
import com.hlopezg.domain.usecase.GetTvsUseCase
import com.hlopezg.presentation_common.navigation.NavRoutes
import com.hlopezg.presentation_common.navigation.TvInput
import com.hlopezg.presentation_common.state.MviViewModel
import com.hlopezg.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvListViewModel @Inject constructor(
    private val getTvsUseCase: GetTvsUseCase,
    private val converter: TvListConverter,
) : MviViewModel<TvListModel, UiState<TvListModel>, TvListUiAction, TvListUiSingleEvent>() {
    override fun initState(): UiState<TvListModel> = UiState.Loading

    override fun handleAction(action: TvListUiAction) {
        when (action) {
            TvListUiAction.Load -> loadTvs()
            is TvListUiAction.SingleMovieClick -> {
                submitSingleEvent(
                    TvListUiSingleEvent.OpenTvScreen(
                        NavRoutes.Tv.routeForTv(
                            TvInput(action.tv.id)
                        )
                    )
                )
            }
        }
    }

    private fun loadTvs() {
        viewModelScope.launch {
            getTvsUseCase.execute(GetTvsUseCase.Request).map {
                converter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }
}