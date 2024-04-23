package com.hlopezg.data_remote.networking.movie

import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    @GET("/3/discover/movie")
    suspend fun getDiscoverMovies(): DiscoverMovieApiModel

    @GET("/3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") idMovie: Long): MovieDetailApiModel
}