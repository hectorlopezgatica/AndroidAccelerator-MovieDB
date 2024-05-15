package com.hlopezg.domain

import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.Tv

abstract class FakeRepositoryFactory {
    abstract fun getMovie(): Movie
    abstract fun getMovies(): List<Movie>

    abstract fun getTv(): Tv
    abstract fun getTvs(): List<Tv>

    abstract fun getGenres(): List<Genre>
    abstract fun getGenre(): Genre
}

class DomainTestUtilities : FakeRepositoryFactory() {
    private val genre1 = listOf(
        Genre(16, "Animation"),
        Genre(28, "Action"),
        Genre(10751, "Family")
    )
    private val genre2 = listOf(
        Genre(28, "Action"),
        Genre(27, "Horror"),
        Genre(53, "Thriller")
    )
    private val genres3 = listOf(Genre(18, "Drama"), Genre(10766, "Soap"))
    private val genres4 = listOf(Genre(10767, "Talk"), Genre(35, "Comedy"))

    private val movie1 = Movie(
        id = 1L,
        adult = true,
        genreIds = genre1,
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
    private val movie2 = Movie(
        id = 2L,
        adult = true,
        genreIds = genre2,
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

    private val tv1 = Tv(
        id = 1L,
        adult = false,
        genreIds = genres3,
        originalLanguage = "de",
        originalName = "Sturm der Liebe",
        overview = "These are the stories of relationships taking place in the fictional five-star hotel Fürstenhof, located in Feldkirchen-Westerham near Rosenheim with the plot revolving around members of the family room area, the hotel owners, and employees.",
        popularity = 3180.01,
        posterPath = "/9oZjOh3Va3FsiLGouhSogFsBX9G.jpg",
        releaseDate = "2005-09-26",
        title = "Sturm der Liebe",
        voteAverage = 5.885,
        voteCount = 26,
    )

    private val tv2 = Tv(
        id = 1L,
        adult = false,
        genreIds = genres4,
        originalLanguage = "en",
        originalName = "Watch What Happens Live with Andy Cohen",
        overview = "Bravo network executive Andy Cohen discusses pop culture topics with celebrities and reality show personalities.",
        popularity = 3755.367,
        posterPath = "/onSD9UXfJwrMXWhq7UY7hGF2S1h.jpg",
        releaseDate = "2009-07-16",
        title = "Watch What Happens Live with Andy Cohen",
        voteAverage = 5.17,
        voteCount = 47,
    )

    private val tvs = listOf(tv1, tv2)

    companion object {
        fun getFakeRepository(): DomainTestUtilities = DomainTestUtilities()
    }

    override fun getMovie(): Movie = movie1
    override fun getMovies(): List<Movie> = movies
    override fun getTv(): Tv = tv1

    override fun getTvs(): List<Tv> = tvs
    override fun getGenres(): List<Genre> {
        return listOf(genre1 + genre2 + genres3 + genres4).flatMap(List<Genre>::toList).toList()
    }

    override fun getGenre(): Genre = genre1.first()
}