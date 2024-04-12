package com.hlopezg.data_local.mapper

import com.hlopezg.data_local.movie.GenreEntity
import com.hlopezg.data_local.movie.MovieEntity
import com.hlopezg.data_local.movie.MovieWithGenres
import com.hlopezg.data_local.tv.TvEntity
import com.hlopezg.data_local.tv.TvWithGenres
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.entity.Tv

fun MovieWithGenres.toMovie() =
    Movie(
        id = this.movieEntity.movieId,
        adult = this.movieEntity.adult,
        backdropPath = this.movieEntity.backdropPath,
        genreIds = this.genres.map { it.toGenre() },
        originalTitle = this.movieEntity.originalTitle,
        originalLanguage = this.movieEntity.originalLanguage,
        overview = this.movieEntity.overview,
        posterPath = this.movieEntity.posterPath,
        popularity = this.movieEntity.popularity,
        releaseDate = this.movieEntity.releaseDate,
        title = this.movieEntity.title,
        voteCount = this.movieEntity.voteCount,
        video = this.movieEntity.video,
        voteAverage = this.movieEntity.voteAverage,
    )

fun Movie.toMovieEntity() =
    MovieEntity(
        movieId = id,
        adult = adult,
        backdropPath = backdropPath,
        originalTitle = originalTitle,
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

fun TvWithGenres.toTv() =
    Tv(
        id = this.tvEntity.id,
        adult = this.tvEntity.adult,
        backdropPath = this.tvEntity.backdropPath,
        genreIds = this.genres.map { it.toGenre() },
        originalName = this.tvEntity.originalTitle,
        originalLanguage = this.tvEntity.originalLanguage,
        overview = this.tvEntity.overview,
        posterPath = this.tvEntity.posterPath,
        popularity = this.tvEntity.popularity,
        releaseDate = this.tvEntity.releaseDate,
        title = this.tvEntity.title,
        voteCount = this.tvEntity.voteCount,
        video = this.tvEntity.video,
        voteAverage = this.tvEntity.voteAverage,
    )

fun Tv.toTvEntity() =
    TvEntity(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalTitle = originalName,
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
fun GenreEntity.toGenre() =
    Genre(
        id = genreId,
        name = name,
    )