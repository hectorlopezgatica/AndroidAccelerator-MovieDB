package com.hlopezg.data_remote.networking.genre

import com.google.gson.annotations.SerializedName

data class GenreApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)
