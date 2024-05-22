package com.hlopezg.domain.entity

data class Discover(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)