package com.hlopezg.data_remote.networking.movie

import com.google.gson.annotations.SerializedName


data class DiscoverApiModel(
    @SerializedName("page") val page: Long,
    @SerializedName("results") val results: List<MovieApiModel>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)