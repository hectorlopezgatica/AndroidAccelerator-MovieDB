package com.hlopezg.presentation_tv.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_tv.navigate.ScreenTvDetail
import com.hlopezg.presentation_tv.single.TvModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TvListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: TvListViewModel,
    navigateToTv: (CommonContentDetail) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(TvListUiAction.Load)
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is TvListUiSingleEvent.OpenTvScreen -> {
                    navigateToTv(
                        ScreenTvDetail(
                            id = it.tv.id,
                            title = it.tv.title,
                            posterPath = it.tv.posterPath,
                            overview = it.tv.overview,
                        )
                    )
                }
            }
        }
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Text(text = "Tv shows")
            TvList(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                it,
            ) { tvModel ->
                viewModel.submitAction(action = TvListUiAction.SingleMovieClick(tvModel))
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TvList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    tvListModel: TvListModel,
    onTvClick: (TvModel) -> Unit,
) {
    with(sharedTransitionScope) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.semantics {
                this.contentDescription = "Tvs LazyRow"
            },
        ) {
            itemsIndexed(tvListModel.items) { index, item ->
                AsyncImage(
                    model = item.posterPath,
                    contentDescription = "Image Tv $index",
                    modifier = Modifier
                        .width(100.dp)
                        .clickable {
                            onTvClick(item)
                        }
                        .sharedElement(
                            state = rememberSharedContentState(key = "image/${item.id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 1000)
                            }
                        )
                )
            }
        }
    }
}