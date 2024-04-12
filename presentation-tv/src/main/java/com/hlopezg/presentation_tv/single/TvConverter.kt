package com.hlopezg.presentation_tv.single

import com.hlopezg.domain.usecase.GetTvUseCase
import com.hlopezg.presentation_common.state.CommonResultConverter
import com.hlopezg.presentation_tv.mapper.toItemModel
import javax.inject.Inject

class TvConverter @Inject constructor() :
    CommonResultConverter<GetTvUseCase.Response, TvModel>() {
    override fun convertSuccess(data: GetTvUseCase.Response): TvModel =
        data.tv.toItemModel()
}