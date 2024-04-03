package com.hlopezg.presentation_movie.list

import android.content.Context
import com.hlopezg.domain.entity.Genre
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.domain.usecase.GetMoviesUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetMoviesUseCase.Response, MovieListModel>() {
    override fun convertSuccess(data: GetMoviesUseCase.Response): MovieListModel {
        return MovieListModel(
            page = 1,
            items = data.movies.map {
                it.toItemModel()
            }
        )
    }

}

private val imageBaseUrl = "https://image.tmdb.org/t/p/original"

fun Movie.toItemModel() =
    MovieListItemModel(
        id = id,
        adult = adult,
        backdropPath = "$imageBaseUrl$backdropPath",
        genreIds = genreIds.map { it.toGenreItemModel() },
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = "$imageBaseUrl$posterPath",
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        video = video,
        voteAverage = voteAverage,
    )

fun Genre.toGenreItemModel() =
    GenreItemModel(
        id = id,
        name = name,
    )
