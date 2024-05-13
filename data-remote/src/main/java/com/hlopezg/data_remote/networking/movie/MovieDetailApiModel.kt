package com.hlopezg.data_remote.networking.movie

import com.google.gson.annotations.SerializedName
import com.hlopezg.data_remote.networking.genre.GenreApiModel


data class MovieDetailApiModel(
  @SerializedName("id") var id: Long,
  @SerializedName("adult") val adult: Boolean,
  @SerializedName("genres") val genreIds: List<GenreApiModel>,
  @SerializedName("original_language") val originalLanguage: String,
  @SerializedName("original_title") val originalTitle: String,
  @SerializedName("overview") val overview: String,
  @SerializedName("popularity") val popularity: Double,
  @SerializedName("poster_path") val posterPath: String,
  @SerializedName("release_date") val releaseDate: String,
  @SerializedName("title") val title: String,
  @SerializedName("video") val video: Boolean,
  @SerializedName("vote_average") val voteAverage: Double,
  @SerializedName("vote_count") val voteCount: Int
)
