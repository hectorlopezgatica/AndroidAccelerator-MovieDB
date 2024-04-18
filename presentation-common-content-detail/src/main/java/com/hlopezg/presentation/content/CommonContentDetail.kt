package com.hlopezg.presentation.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.utils.Utils

@Composable
fun CommonDetailScreen(
    commonContentDetail: CommonContentDetail,
){
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = commonContentDetail.posterPath,
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            Text(text = commonContentDetail.title)
            if (commonContentDetail.genreIds.isNotEmpty()) {
                Row {
                    for ((index, genre) in commonContentDetail.genreIds.withIndex()) {
                        Text(text = genre.name)
                        if (index != commonContentDetail.genreIds.size - 1) {
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
                    progress = { commonContentDetail.getUserScore().toFloat() / 100 },
                    color = Utils.getScoreColor(commonContentDetail.getUserScore()),
                )
                Text(text = "${commonContentDetail.getUserScore()}%")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = commonContentDetail.overview)
        }
    }
}