package com.hlopezg.presentation_tv.single

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.hlopezg.presentation.content.CommonDetailScreen
import com.hlopezg.presentation_common.CommonScreen
import com.hlopezg.presentation_common.navigation.TvInput

@Composable
fun TvScreen(
    viewModel: TvViewModel,
    tvInput: TvInput,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(TvUiAction.Load(tvInput.tvId))
    }

    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(result) { movieModel ->
            CommonDetailScreen(movieModel)
        }
    }
}