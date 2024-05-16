package com.hlopezg.presentation_tv.single

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hlopezg.presentation.content.CommonDetailScreen
import com.hlopezg.presentation.content.PosterPane
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.navigation.TvInput

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TvScreenDetail(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: TvViewModel,
    commonContentDetail: CommonContentDetail,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(TvUiAction.Load(commonContentDetail.id))
    }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        PosterPane(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            posterPath = commonContentDetail.posterPath
        )
        viewModel.uiStateFlow.collectAsState().value.let { result ->
            CommonScreen(result) { movieModel ->
                CommonDetailScreen(
                    animatedVisibilityScope = animatedVisibilityScope,
                    sharedTransitionScope = sharedTransitionScope,
                    commonContentDetail = movieModel,
                )
            }
        }
    }
}