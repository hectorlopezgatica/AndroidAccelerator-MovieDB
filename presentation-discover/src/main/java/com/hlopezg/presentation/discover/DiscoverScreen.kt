package com.hlopezg.presentation.discover

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hlopezg.presentation_movie.list.MovieListScreen
import com.hlopezg.presentation_movie.list.MovieListViewModel
import com.hlopezg.presentation_tv.list.TvListScreen
import com.hlopezg.presentation_tv.list.TvListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DiscoverListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    movieViewModel: MovieListViewModel,
    tvViewModel: TvListViewModel,
    navController: NavController,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        MovieListScreen(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            viewModel = movieViewModel,
            navigateToMovie = {
                navController.navigate(it)
            }
        )
        Spacer(modifier = Modifier.size(8.dp))
        TvListScreen(
            animatedVisibilityScope = animatedVisibilityScope,
            sharedTransitionScope = sharedTransitionScope,
            viewModel = tvViewModel,
            navigateToTv = {
                navController.navigate(it)
            }
        )
    }
}