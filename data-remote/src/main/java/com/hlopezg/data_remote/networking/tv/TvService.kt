package com.hlopezg.data_remote.networking.tv

import retrofit2.http.GET
import retrofit2.http.Path

interface TvService {
    @GET("/genre/movie/list")
    suspend fun getMovies(): List<TvApiModel>

    @GET("/genre/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") idMovie: Long): TvApiModel
}