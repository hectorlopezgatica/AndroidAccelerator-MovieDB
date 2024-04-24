package com.hlopezg.presentation_tv.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hlopezg.presentation.content.CommonDetailScreen
import com.hlopezg.presentation_common.component.CommonScreen
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
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                CommonDetailScreen(
                    commonContentDetail = movieModel,
                )
            }
        }
    }
}