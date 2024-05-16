package com.hlopezg.presentation.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.models.GenreModel
import com.hlopezg.presentation_common.navigation.NavRoutes

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showSystemUi = true)
@Composable
fun PreviewCommonDetailScreen() {
    val genres = listOf(
        GenreModel(id = 16, name = "Animation"),
        GenreModel(id = 28, name = "Action"),
        GenreModel(id = 10751, name = "Family"),
        GenreModel(id = 35, name = "Comedy"),
        GenreModel(id = 14, name = "Fantasy")
    )
    SharedTransitionLayout{
        AnimatedContent (
            true,
            label = "basic_transition"
        ){targetState ->
            if(targetState){
                CommonDetailScreen(
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    PreviewContentDetail(
                        "Kung Fu Panda",
                        "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/ajnzOECvXpa7VcVx0RSlq39XgHe.jpg",
                        "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.",
                        genres,
                        id = 1L
                    )
                )
            }
        }
    }
}

data class PreviewContentDetail(
    override val title: String,
    override val posterPath: String,
    override val overview: String,
    val genreIds: List<GenreModel>,
    override val id: Long

) : CommonContentDetail {
    override fun getUserScore(): Int = 80
}