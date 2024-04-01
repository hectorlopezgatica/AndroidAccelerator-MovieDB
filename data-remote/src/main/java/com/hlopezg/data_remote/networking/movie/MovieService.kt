package com.hlopezg.data_remote.networking.movie

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("/genre/movie/list")
    suspend fun getMovies(): List<MovieApiModel>

    @GET("/genre/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") idMovie: Long): MovieApiModel
}