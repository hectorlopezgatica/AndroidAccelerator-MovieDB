package com.hlopezg.presentation_movie.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
