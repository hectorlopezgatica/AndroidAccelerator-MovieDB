package com.hlopezg.data_remote.networking.tv

import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {
    @GET("/3/discover/tv")
    suspend fun discoverTvs(): DiscoverTvApiModel

    @GET("/3/tv/{movie_id}")
    suspend fun getMovie(@Path("movie_id") idMovie: Long): TvApiModel
}