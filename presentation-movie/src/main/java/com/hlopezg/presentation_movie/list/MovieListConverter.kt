package com.hlopezg.presentation_movie.list

import android.content.Context
import com.hlopezg.domain.usecase.GetMoviesUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_movie.mapper.toItemModel
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