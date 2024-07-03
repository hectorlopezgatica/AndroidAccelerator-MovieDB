package com.hlopezg.presentation_movie.single

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import com.hlopezg.presentation.content.CommonContentDetailViewModel
import com.hlopezg.presentation.content.components.CommonDetailPane
import com.hlopezg.presentation.content.components.PosterPane
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.models.CommonContentDetail

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieScreenDetail(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    commonContentDetail: CommonContentDetail,
    modifier: Modifier = Modifier,
    contentDescription: String = "Poster Pane",
    commonContentDetailViewModel: CommonContentDetailViewModel = hiltViewModel(),
    viewModel: MovieViewModel = hiltViewModel(),
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(MovieUiAction.Load(commonContentDetail.id))
    }
    val configuration = LocalConfiguration.current

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = modifier
                .conditional(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    fillMaxWidth(0.6f)
                }
                .verticalScroll(rememberScrollState())
                .semantics {
                    this.contentDescription = contentDescription
                }
        ) {
            PosterPane(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                commonContentDetail = commonContentDetail,
            )
            viewModel.uiStateFlow.collectAsState().value.let { result ->
                CommonScreen(result) { movieModel ->
                    CommonDetailPane(
                        commonContentDetail = movieModel,
                        viewModel = commonContentDetailViewModel,
                    )
                }
            }
        }
    }

}

fun Modifier.conditional(condition: Boolean, modifier: Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}