package com.hlopezg.presentation_tv.single

import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Tv
import com.hlopezg.domain.usecase.GetTvUseCase
import com.hlopezg.presentation_common.mapper.toGenreItemModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TvConverterTest {
    private val converter = TvConverter()

    private val id = 1L
    private val adult = true
    private val backdropPath = "/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg"
    private val genreIds = listOf(Genre(16, "Animation"), Genre(28, "Action"), Genre(10751, "Family"))
    private val originalName = "Kung Fu Panda 4"
    private val originalLanguage = "en"
    private val overview = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past."
    private val posterPath = "/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg"
    private val popularity = 1665.369
    private val releaseDate = "2024-03-02"
    private val title = "Kung Fu Panda 4"
    private val voteCount = 1235
    private val video = false
    private val voteAverage = 7.155

    @Test
    fun testConvertSuccess(){
        val response = GetTvUseCase.Response(
            tv = Tv(
                id = id,
                adult = adult,
                backdropPath = backdropPath,
                genreIds = genreIds,
                originalName = originalName,
                originalLanguage = originalLanguage,
                overview = overview,
                posterPath = posterPath,
                popularity = popularity,
                releaseDate = releaseDate,
                title = title,
                voteCount = voteCount,
                video = video,
                voteAverage = voteAverage,
            )
        )
        val result = converter.convertSuccess(response)

        val movieModel = TvModel(
            id = id,
            adult = adult,
            backdropPath = "https://image.tmdb.org/t/p/original$backdropPath",
            genreIds = genreIds.map { it.toGenreItemModel() },
            originalName = originalName,
            originalLanguage = originalLanguage,
            overview = overview,
            posterPath = "https://image.tmdb.org/t/p/original$posterPath",
            popularity = popularity,
            releaseDate = releaseDate,
            title = title,
            voteCount = voteCount,
            video = video,
            voteAverage = voteAverage,
        )
        assertEquals(movieModel, result)
    }
}