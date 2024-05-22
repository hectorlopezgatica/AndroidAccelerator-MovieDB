package com.hlopezg.data_remote

import com.hlopezg.data_remote.networking.genre.GenreApiModel
import com.hlopezg.data_remote.networking.movie.MovieApiModel
import com.hlopezg.data_remote.networking.tv.TvApiModel
import com.hlopezg.domain.entity.Genre

interface FakeRepositoryFactory {
    fun getMovieApiModel(): MovieApiModel
    fun getMoviesApiModel(): List<MovieApiModel>
    fun getTvApiModel(): TvApiModel
    fun getTvsApiModel(): List<TvApiModel>
}

class LocalDataTestUtilities : FakeRepositoryFactory {
    private val genre1 = listOf(
        GenreApiModel(16, "Animation"),
        GenreApiModel(28, "Action"),
        GenreApiModel(10751, "Family")
    )
    private val genre2 = listOf(
        GenreApiModel(28, "Action"),
        GenreApiModel(27, "Horror"),
        GenreApiModel(53, "Thriller")
    )
    private val genres3 = listOf(Genre(18, "Drama"), Genre(10766, "Soap"))
    private val genres4 = listOf(Genre(10767, "Talk"), Genre(35, "Comedy"))


    private val movie1 = MovieApiModel(
        id = 1L,
        adult = true,
        genreIds = genre1.map { it.id },
        originalTitle = "Kung Fu Panda 4",
        originalLanguage = "en",
        overview = "Po is gearing up to become the spiritual leader of his Valley of Peace, but also needs someone to take his place as Dragon Warrior. As such, he will train a new kung fu practitioner for the spot and will encounter a villain called the Chameleon who conjures villains from the past.",
        posterPath = "/kDp1vUBnMpe8ak4rjgl3cLELqjU.jpg",
        popularity = 1665.369,
        releaseDate = "2024-03-02",
        title = "Kung Fu Panda 4",
        voteCount = 1235,
        video = false,
        voteAverage = 7.155,
    )

    private val movie2 = MovieApiModel(
        id = 2L,
        adult = true,
        genreIds = genre2.map { it.id },
        originalTitle = "No Way Up",
        originalLanguage = "en",
        overview = "Characters from different backgrounds are thrown together when the plane they're travelling on crashes into the Pacific Ocean. A nightmare fight for survival ensues with the air supply running out and dangers creeping in from all sides.",
        posterPath = "/hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg",
        popularity = 2768.363,
        releaseDate = "2024-01-18",
        title = "No Way Up",
        voteCount = 679,
        video = false,
        voteAverage = 6.478,
    )

    private val movies = listOf(movie1, movie2)

    private val tv1 = TvApiModel(
        id = 1L,
        adult = false,
        genreIds = genres3.map { it.id },
        originalLanguage = "de",
        originalName = "Sturm der Liebe",
        overview = "These are the stories of relationships taking place in the fictional five-star hotel Fürstenhof, located in Feldkirchen-Westerham near Rosenheim with the plot revolving around members of the family room area, the hotel owners, and employees.",
        popularity = 3180.01,
        posterPath = "/9oZjOh3Va3FsiLGouhSogFsBX9G.jpg",
        firstAirDate = "2005-09-26",
        name = "Sturm der Liebe",
        voteAverage = 5.885,
        voteCount = 26,
        video = false,
    )

    private val tv2 = TvApiModel(
        id = 1L,
        adult = false,
        genreIds = genres4.map { it.id },
        originalLanguage = "en",
        originalName = "Watch What Happens Live with Andy Cohen",
        overview = "Bravo network executive Andy Cohen discusses pop culture topics with celebrities and reality show personalities.",
        popularity = 3755.367,
        posterPath = "/onSD9UXfJwrMXWhq7UY7hGF2S1h.jpg",
        firstAirDate = "2009-07-16",
        name = "Watch What Happens Live with Andy Cohen",
        voteAverage = 5.17,
        voteCount = 47,
        video = false,
    )

    private val tvs = listOf(tv1, tv2)
    override fun getMovieApiModel(): MovieApiModel = movie1

    override fun getMoviesApiModel(): List<MovieApiModel> = movies

    override fun getTvApiModel(): TvApiModel = tv1
    override fun getTvsApiModel(): List<TvApiModel> = tvs
}