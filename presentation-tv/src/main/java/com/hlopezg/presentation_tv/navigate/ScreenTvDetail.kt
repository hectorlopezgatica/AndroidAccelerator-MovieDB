package com.hlopezg.presentation_tv.navigate

import com.hlopezg.presentation_common.models.CommonContentDetail
import kotlinx.serialization.Serializable

@Serializable
data class ScreenTvDetail(
    override val id: Long,
    override val title: String,
    override val posterPath: String,
    override val overview: String,

    ) : CommonContentDetail {
    override fun getUserScore(): Int {
        TODO("Not yet implemented")
    }
}

