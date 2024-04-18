package com.hlopezg.presentation_movie.single

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.navigation.MovieInput
import com.hlopezg.presentation_common.state.CommonScreen
import com.hlopezg.presentation_common.utils.Utils

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
            Movie(movieModel)
        }
    }
}

@Composable
fun Movie(movieModel: MovieModel) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = movieModel.posterPath,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            Text(text = movieModel.title)
            if (movieModel.genreIds.isNotEmpty()) {
                Row {
                    for ((index, genre) in movieModel.genreIds.withIndex()) {
                        Text(text = genre.name)
                        if (index != movieModel.genreIds.size - 1) {
                            Text(text = ", ")
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(60.dp)),
                    progress = { movieModel.getUserScore().toFloat() / 100 },
                    color = Utils.getScoreColor(movieModel.getUserScore()),
                )
                Text(text = "${movieModel.getUserScore()}%")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = movieModel.overview)
        }
    }
}