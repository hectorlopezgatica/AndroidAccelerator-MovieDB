package com.hlopezg.data_remote.networking.tv

import com.google.gson.annotations.SerializedName


data class DiscoverTvApiModel(
    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: List<TvApiModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)