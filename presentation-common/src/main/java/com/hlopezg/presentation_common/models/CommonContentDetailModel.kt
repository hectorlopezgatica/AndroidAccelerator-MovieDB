package com.hlopezg.presentation_common.models

import kotlinx.serialization.Serializable

@Serializable
data class CommonContentDetailModel(
    override val id: Long,
    override val title: String,
    override val posterPath: String,
    override val overview: String,
    val genreIds: List<GenreModel>,
): CommonContentDetail {
    override fun getUserScore(): Int {
        return 1
    }
}
