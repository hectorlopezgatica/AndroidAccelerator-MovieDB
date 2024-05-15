package com.hlopezg.presentation_tv.list

import android.content.Context
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.usecase.GetTvsUseCase
import com.hlopezg.presentation_common.mapper.toGenreItemModel
import com.hlopezg.presentation_common.utils.Utils
import com.hlopezg.presentation_tv.single.TvModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock


class TvListConverterTest {
    private val context = mock<Context>()
    private val converter = TvListConverter(context)

    private val id = 1L
    private val adult = true
    private val genreIds = listOf(Genre(16, "Animation"), Genre(28, "Action"), Genre(10751, "Family"))
    private val originalName = "Kung Fu Panda 4"
    private val originalLanguage = "en"
    private val overview = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past."
    private val posterPath = "/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg"
    private val popularity = 1665.369
    private val releaseDate = "2024-03-02"
    private val title = "Kung Fu Panda 4"
    private val voteCount = 1235
    private val voteAverage = 7.155

    @Test
    fun testConvertSuccess(){
        val response = GetTvsUseCase.Response(
            tvList = listOf(
                Tv(
                    id = id,
                    adult = adult,
                    genreIds = genreIds,
                    originalName = originalName,
                    originalLanguage = originalLanguage,
                    overview = overview,
                    posterPath = posterPath,
                    popularity = popularity,
                    releaseDate = releaseDate,
                    title = title,
                    voteCount = voteCount,
                    voteAverage = voteAverage,
                )
            )
        )

        val result = converter.convertSuccess(response)

        val movieModel = TvModel(
            id = id,
            adult = adult,
            genreIds = genreIds.map { it.toGenreItemModel() },
            originalName = originalName,
            originalLanguage = originalLanguage,
            overview = overview,
            posterPath = "${Utils.IMAGE_PATH}$posterPath",
            popularity = popularity,
            releaseDate = releaseDate,
            title = title,
            voteCount = voteCount,
            voteAverage = voteAverage,
        )
        val items = listOf(movieModel)
        val movieListModel = TvListModel(items = items, page = 1)

        assertEquals(movieListModel, result)
    }
}