package com.hlopezg.presentation.content.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hlopezg.presentation.content.CommonContentDetailUiAction
import com.hlopezg.presentation.content.CommonContentDetailViewModel
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.state.UiState
import com.hlopezg.presentation_common.utils.Utils
import com.hlopezg.presentation_common_content_detail.R

@Composable
fun CommonDetailPane(
    commonContentDetail: CommonContentDetail,
    viewModel: CommonContentDetailViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.handleAction(CommonContentDetailUiAction.ShowDetail(commonContentDetail))
    }
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        val whereToWatchPrompt = stringResource(
            R.string.show_me_where_i_can_watch, commonContentDetail.title
        )
        val shouldShowDialog = remember {
            mutableStateOf(false)
        }

        Text(text = commonContentDetail.title)
        /* if (commonContentDetail.genreIds.isNotEmpty()) {
            Row {
                for ((index, genre) in commonContentDetail.genreIds.withIndex()) {
                    Text(text = genre.name)
                    if (index != commonContentDetail.genreIds.size - 1) {
                        Text(text = ", ")
                    }
                }
            }
        }*/
        Spacer(modifier = Modifier.padding(8.dp))
        Box(
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.then(Modifier.size(60.dp)),
                progress = { commonContentDetail.getUserScore().toFloat() / 100 },
                color = Utils.getScoreColor(commonContentDetail.getUserScore()),
            )
            Text(text = "${commonContentDetail.getUserScore()}%")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = commonContentDetail.overview)
        Spacer(modifier = Modifier.padding(8.dp))
        viewModel.uiStateFlow.collectAsState().value.let { state ->
            when (state) {
                is UiState.Error -> {}
                is UiState.Loading -> {
                    Button(onClick = {}) {
                        CircularProgressIndicator(color = Color.Red)
                    }
                }

                is UiState.Success -> {
                    Button(onClick = {
                        shouldShowDialog.value = true
                        viewModel.handleAction(
                            CommonContentDetailUiAction.ShowSuggestWhereToWatch(
                                whereToWatchPrompt
                            )
                        )
                    }) {
                        Text(text = stringResource(R.string.where_I_can_watch))
                    }
                    if (shouldShowDialog.value) {
                        AlertDialog(
                            icon = { },
                            text = {
                                Text(text = state.data.title)
                            },
                            onDismissRequest = { },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        shouldShowDialog.value = false
                                    }
                                ) {
                                    Text(stringResource(R.string.understood))
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}