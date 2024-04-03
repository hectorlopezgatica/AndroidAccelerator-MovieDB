package com.hlopezg.presentation_movie.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.navigation.MovieInput
import com.hlopezg.presentation_common.state.CommonScreen

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    movieInput: MovieInput,
){
    viewModel.uiStateFlow.collectAsState().value.let {result ->
        CommonScreen(result) { movieModel ->
            Movie(movieModel)
        }
    }
}

@Composable
fun Movie(movieModel: MovieModel){
    Row (modifier = Modifier.padding(16.dp)){
        AsyncImage (
            model = movieModel.posterPath,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        Column{
            Text(text = movieModel.title)
            Text(text = movieModel.genreIds.toString())  //TODO: add genre
            Box {
                CircularProgressIndicator(progress = { movieModel.voteAverage.toFloat() })
                Text(text = "${movieModel.voteAverage}")
            }

            Text(text = movieModel.overview)

        }
    }
}