package com.hlopezg.presentation_movie.single

import android.content.Context
import com.hlopezg.domain.usecase.GetMovieUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_movie.mapper.toItemModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MovieConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetMovieUseCase.Response, MovieModel>() {
    override fun convertSuccess(data: GetMovieUseCase.Response): MovieModel =
        data.movie.toItemModel()
}