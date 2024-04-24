package com.hlopezg.presentation_movie.single

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.hlopezg.presentation.content.CommonDetailScreen
import com.hlopezg.presentation_common.component.CommonScreen
import com.hlopezg.presentation_common.navigation.MovieInput

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    movieInput: MovieInput,
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.handleAction(MovieUiAction.Load(movieInput.movieId))
    }

    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(result) { movieModel ->
            CommonDetailScreen(
                commonContentDetail = movieModel,
            )
        }
    }
}
