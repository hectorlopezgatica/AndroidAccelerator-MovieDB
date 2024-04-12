package com.hlopezg.presentation.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hlopezg.presentation_common.state.CommonScreen
import com.hlopezg.presentation_movie.list.MovieListScreen
import com.hlopezg.presentation_movie.list.MovieListViewModel
import com.hlopezg.presentation_tv.list.TvListScreen
import com.hlopezg.presentation_tv.list.TvListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.zip

@Composable
fun DiscoverListScreen(
    movieViewModel: MovieListViewModel,
    tvViewModel: TvListViewModel,
    //navController: NavController,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        MovieListScreen(
            viewModel = movieViewModel,
        )
        TvListScreen(viewModel = tvViewModel)
    }
/*
    movieViewModel.uiStateFlow.combine(tvViewModel.uiStateFlow){state1, state2 ->
        CommonScreen(states = arrayOf(state1, state2)) {
            Column(modifier = Modifier.padding(16.dp)) {
                MovieListScreen(
                    viewModel = movieViewModel,
                )
                TvListScreen(viewModel = tvViewModel)
            }
        }
    }
*/

/*    movieViewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Column(modifier = Modifier.padding(16.dp)) {
                MovieListScreen(
                    viewModel = movieViewModel,
                )
                TvListScreen(viewModel = tvViewModel)
            }
        }
    }*/
}