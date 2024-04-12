package com.hlopezg.presentation_tv.list

import android.content.Context
import com.hlopezg.domain.usecase.GetTvsUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_tv.mapper.toItemModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TvListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetTvsUseCase.Response, TvListModel>() {
    override fun convertSuccess(data: GetTvsUseCase.Response): TvListModel {
        return TvListModel(
            page = 1,
            items = data.movies.map {
                it.toItemModel()
            }
        )
    }
}