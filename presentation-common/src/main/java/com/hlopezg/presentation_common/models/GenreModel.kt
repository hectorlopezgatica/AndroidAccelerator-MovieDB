package com.hlopezg.presentation_common.models

import kotlinx.serialization.Serializable

@Serializable
data class GenreModel(
    val id: Int,
    val name: String,
)