package com.hlopezg.presentation.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hlopezg.presentation_common.models.CommonContentDetail
import com.hlopezg.presentation_common.models.GenreModel

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
    CommonDetailScreen(
        PreviewContentDetail(
            "Kung Fu Panda",
            "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/ajnzOECvXpa7VcVx0RSlq39XgHe.jpg",
            "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.",
            genres
        )
    )
}

data class PreviewContentDetail(
    override val title: String,
    override val posterPath: String,
    override val overview: String,
    override val genreIds: List<GenreModel>

) : CommonContentDetail {
    override fun getUserScore(): Int = 80
}