package com.hlopezg.presentation_movie.single

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
import com.hlopezg.presentation_common.navigation.MovieInput

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieScreenDetail(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: MovieViewModel,
    commonContentDetail: CommonContentDetail,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(MovieUiAction.Load(commonContentDetail.id))
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
