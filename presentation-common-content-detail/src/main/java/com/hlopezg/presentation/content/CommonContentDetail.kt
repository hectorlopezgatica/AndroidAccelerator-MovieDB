package com.hlopezg.presentation.content

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.utils.Utils
import com.hlopezg.presentation_common_content_detail.R

@Composable
fun CommonDetailScreen(
    commonContentDetail: CommonContentDetail,
) {
    AdaptativePane(
        commonContentDetail = commonContentDetail,
    )
}

@Composable
fun AdaptativePane(
    commonContentDetail: CommonContentDetail,
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            TwoPane(commonContentDetail)
        }

        else -> {
            OnePane(commonContentDetail)
        }
    }
}

@Composable
fun OnePane(
    commonContentDetail: CommonContentDetail,
) {
    Column {
        FirstPane(posterPath = commonContentDetail.posterPath)
        SecondPane(commonContentDetail = commonContentDetail)
    }
}

@Composable
fun TwoPane(
    commonContentDetail: CommonContentDetail,
) {
    Row(modifier = Modifier.padding(16.dp)) {
        FirstPane(posterPath = commonContentDetail.posterPath)
        Column {
            SecondPane(commonContentDetail = commonContentDetail)
        }
    }
}


@Composable
fun FirstPane(
    posterPath: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = posterPath,
            placeholder = painterResource(id = R.drawable.image_fallout),
            contentDescription = null,
        )
    }
}

@Composable
fun SecondPane(
    commonContentDetail: CommonContentDetail,
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
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