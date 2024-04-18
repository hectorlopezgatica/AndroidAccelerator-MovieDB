package com.hlopezg.presentation_common.utils

import androidx.compose.ui.graphics.Color

class Utils {
    companion object Keys{
        const val IMAGE_PATH = "https://image.tmdb.org/t/p/original"
        fun getScoreColor(score: Int): Color {
            return when (score) {
                in 80..100 -> {
                    Color.Green
                }
                in 60..79 -> {
                    Color.Yellow
                }
                else -> {
                    Color.Red
                }
            }
        }
    }
}