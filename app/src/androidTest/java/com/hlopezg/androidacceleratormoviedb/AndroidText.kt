package com.hlopezg.androidacceleratormoviedb

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AndroidText {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initDiscoverMoviesScreen() {
        rule.waitUntil(5000L) {
            rule
                .onNodeWithContentDescription("Image Movie 0")
                .isDisplayed()
        }

        rule.onNodeWithContentDescription("Movies LazyRow").assertExists()

        rule.onNodeWithContentDescription("Image Movie 0").performClick()

        rule.waitUntil(5000L) {
            rule
                .onNodeWithContentDescription("Poster Pane")
                .isDisplayed()
        }

        rule.onNodeWithContentDescription("Poster Pane").assertExists()
    }

    @Test
    fun initDiscoverTvsScreen() {
        rule.waitUntil(5000L) {
            rule
                .onNodeWithContentDescription("Image Tv 0")
                .isDisplayed()
        }

        rule.onNodeWithContentDescription("Tvs LazyRow").assertExists()

        rule.onNodeWithContentDescription("Image Tv 0").performClick()

        rule.waitUntil(5000L) {
            rule
                .onNodeWithContentDescription("Poster Pane")
                .isDisplayed()
        }

        rule.onNodeWithContentDescription("Poster Pane").assertExists()
    }
}