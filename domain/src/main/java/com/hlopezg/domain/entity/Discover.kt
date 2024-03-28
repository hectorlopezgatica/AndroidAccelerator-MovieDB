package com.hlopezg.domain.entity

data class Discover (
    val page: Int,
    val movie: Movie,
    val totalPages: Int,
    val totalResults: Int
){

}