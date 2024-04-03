package com.hlopezg.presentation_movie.single

import android.content.Context
import com.hlopezg.domain.entity.Movie
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_movie.list.MovieListItemModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.math.roundToInt

class MovieConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetMovieUseCase.Response, MovieModel>() {
    override fun convertSuccess(data: GetMovieUseCase.Response): MovieModel = data.movie.toMovieModel()
}

private val imageBaseUrl = "https://image.tmdb.org/t/p/original"

fun Movie.toMovieModel() =
    MovieModel(
        id = id,
        adult = adult,
        backdropPath = "$imageBaseUrl$backdropPath",
        genreIds = genreIds.map { GenreModel(it.id, it.name)  },
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        overview = overview,
        posterPath = "$imageBaseUrl$posterPath",
        popularity = popularity,
        releaseDate = releaseDate,
        title = title,
        voteCount = voteCount,
        video = video,
        voteAverage = (voteAverage * 100).roundToInt(),
    )