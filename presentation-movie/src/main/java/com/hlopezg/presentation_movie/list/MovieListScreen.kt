package com.hlopezg.presentation_movie.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.state.CommonScreen
import com.hlopezg.presentation_movie.single.MovieModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel,
    navController: NavController,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(MovieListUiAction.Load)
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Movies")
                MovieList(
                    it
                ) { movieModel ->
                    viewModel.submitAction(action = MovieListUiAction.SingleMovieClick(movieModel))
                }
            }
        }
    }
}

@Composable
fun MovieList(
    movieListModel: MovieListModel,
    onMovieClick: (MovieListItemModel) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
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
            )
        }
    }
}