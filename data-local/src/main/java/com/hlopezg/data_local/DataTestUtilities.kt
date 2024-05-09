package com.hlopezg.data_local

import com.hlopezg.data_local.genre.GenreEntity
import com.hlopezg.data_local.mapper.toGenreEntity
import com.hlopezg.data_local.mapper.toMovieEntity
import com.hlopezg.data_local.mapper.toTvEntity
import com.hlopezg.data_local.movie.MovieWithGenres
import com.hlopezg.data_local.tv.TvWithGenres
import com.hlopezg.domain.TestUtilities

interface FakeRepositoryFactory{
    fun getMovieWithGenres(): MovieWithGenres
    fun getMoviesWithGenres(): List<MovieWithGenres>
    fun getTvWithGenres(): TvWithGenres
    fun getTvsWithGenres(): List<TvWithGenres>

    fun getGenres(): List<GenreEntity>
}
class DataTestUtilities: FakeRepositoryFactory {
    private val movies = TestUtilities.getFakeRepository().getMovies()
    private val movieWithGenres1 = MovieWithGenres(
        movieEntity = movies[0].toMovieEntity(),
        genres = movies[0].genreIds.map { GenreEntity(genreId = it.id, name = it.name) }
    )

    private val movieWithGenres2 = MovieWithGenres(
        movieEntity = movies[1].toMovieEntity(),
        genres = movies[1].genreIds.map { GenreEntity(genreId = it.id, name = it.name) }
    )

    private val moviesWithGenres = listOf(movieWithGenres1, movieWithGenres2)

    private val tvs = TestUtilities.getFakeRepository().getTvs()
    private val tvWithGenres1 = TvWithGenres(
        tvEntity = tvs[0].toTvEntity(),
        genres = tvs[0].genreIds.map { GenreEntity(genreId = it.id, name = it.name) }
    )

    private val tvWithGenres2 = TvWithGenres(
        tvEntity = tvs[1].toTvEntity(),
        genres = tvs[1].genreIds.map { GenreEntity(genreId = it.id, name = it.name) }
    )

    private val tvsWithGenres = listOf(tvWithGenres1, tvWithGenres2)

    companion object {
        fun getFakeRepository(): DataTestUtilities = DataTestUtilities()
    }

    override fun getMovieWithGenres(): MovieWithGenres = movieWithGenres1

    override fun getMoviesWithGenres(): List<MovieWithGenres> = moviesWithGenres

    override fun getTvWithGenres(): TvWithGenres = tvWithGenres1

    override fun getTvsWithGenres(): List<TvWithGenres> = tvsWithGenres
    override fun getGenres(): List<GenreEntity> =
        TestUtilities.getFakeRepository().getGenres().map { it.toGenreEntity(1) }
}