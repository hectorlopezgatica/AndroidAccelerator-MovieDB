package com.hlopezg.presentation_common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun PreviewLoadingComponentDetail(){
    LoadingComponentDetail()
}

@Composable
fun LoadingComponentDetail() {
    Column(modifier = Modifier.padding(16.dp)
    ) {
        ComponentRectangleFullSize(false)
        ComponentRectangleLineShort(Modifier.padding(paddingValues = PaddingValues(top = 8.dp, bottom = 8.dp)))
        ComponentRectangleLineLong(Modifier.padding(paddingValues = PaddingValues(bottom = 8.dp)))
        ComponentCircle(Modifier.padding(paddingValues = PaddingValues(bottom = 8.dp)))
        ComponentRectangleParagraphLong(Modifier.padding(paddingValues = PaddingValues(bottom = 8.dp)))
    }
}