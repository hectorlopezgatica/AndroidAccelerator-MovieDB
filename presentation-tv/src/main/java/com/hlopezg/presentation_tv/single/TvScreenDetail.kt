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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.hlopezg.presentation.content.CommonDetailScreen
import com.hlopezg.presentation.content.PosterPane
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.models.CommonContentDetail

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TvScreenDetail(
    contentDescription: String = "Poster Pane",
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: TvViewModel,
    commonContentDetail: CommonContentDetail,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(TvUiAction.Load(commonContentDetail.id))
    }
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).semantics {
            this.contentDescription = contentDescription
        },
    ) {
        PosterPane(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            commonContentDetail = commonContentDetail,
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