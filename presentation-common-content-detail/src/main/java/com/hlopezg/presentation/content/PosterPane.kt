package com.hlopezg.presentation.content

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import coil.compose.AsyncImage
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common_content_detail.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PosterPane(
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    commonContentDetail: CommonContentDetail,
) {
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image/${commonContentDetail.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1000)

                        }
                    )
                    .semantics {
                        this.contentDescription = "Detail Image"
                    },
                model = commonContentDetail.posterPath,
                placeholder = painterResource(id = R.drawable.image_fallout),
                contentDescription = null,
            )
        }
    }
}
