package com.hlopezg.presentation_movie.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_movie.navigate.ScreenMovieDetail
import com.hlopezg.presentation_movie.single.MovieModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    viewModel: MovieListViewModel,
    navigateToMovie: (CommonContentDetail) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(MovieListUiAction.Load)
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is MovieListUiSingleEvent.OpenMovieScreen -> {
                    navigateToMovie(
                        ScreenMovieDetail(
                            id = it.movie.id,
                            title = it.movie.title,
                            posterPath = it.movie.posterPath,
                            overview = it.movie.overview
                        )
                    )
                }
            }
        }
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Text(text = "Movies")
            MovieList(
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                it
            ) { movieModel ->
                viewModel.submitAction(action = MovieListUiAction.SingleMovieClick(movieModel))
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MovieList(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    movieListModel: MovieListModel,
    onMovieClick: (MovieModel) -> Unit,
) {
    with(sharedTransitionScope) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movieListModel.items) { item ->
                AsyncImage(
                    model = item.posterPath,
                    contentDescription = null,
                    modifier = Modifier
                        .width(100.dp)
                        .clickable {
                            onMovieClick(item)
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