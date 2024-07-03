package com.hlopezg.presentation.content.components

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.hlopezg.presentation.content.CommonContentDetailViewModel
import com.hlopezg.presentation_common.models.CommonContentDetail

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CommonDetailScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    commonContentDetail: CommonContentDetail,
    viewModel: CommonContentDetailViewModel,
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            TwoPane(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                commonContentDetail = commonContentDetail,
                viewModel = viewModel,
            )
        }

        else -> {
            OnePane(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                commonContentDetail = commonContentDetail,
                viewModel = viewModel,
            )
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OnePane(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    commonContentDetail: CommonContentDetail,
    viewModel: CommonContentDetailViewModel,
) {
    Column {
        CommonDetailPane(
            commonContentDetail = commonContentDetail,
            viewModel = viewModel,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TwoPane(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    commonContentDetail: CommonContentDetail,
    viewModel: CommonContentDetailViewModel,
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Column {
            CommonDetailPane(
                commonContentDetail = commonContentDetail,
                viewModel = viewModel,
            )
        }
    }
}