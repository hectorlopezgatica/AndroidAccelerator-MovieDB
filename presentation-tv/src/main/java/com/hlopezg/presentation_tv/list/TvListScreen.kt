package com.hlopezg.presentation_tv.list

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
import com.hlopezg.presentation_common.CommonScreen
import com.hlopezg.presentation_tv.single.TvModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TvListScreen(
    viewModel: TvListViewModel,
    navigateToTv: (String) -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.submitAction(TvListUiAction.Load)
        viewModel.singleEventFlow.collectLatest {
            when (it) {
                is TvListUiSingleEvent.OpenTvScreen -> {
                    navigateToTv(it.navRoute)
                }
            }
        }
    }

    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) {
            Text(text = "Tv shows")
            TvList(
                it
            ) { tvModel ->
                viewModel.submitAction(action = TvListUiAction.SingleMovieClick(tvModel))
            }
        }
    }
}

@Composable
fun TvList(
    tvListModel: TvListModel,
    onTvClick: (TvModel) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(tvListModel.items) { item ->
            AsyncImage(
                model = item.posterPath,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .clickable {
                        onTvClick(item)
                    }
            )
        }
    }
}