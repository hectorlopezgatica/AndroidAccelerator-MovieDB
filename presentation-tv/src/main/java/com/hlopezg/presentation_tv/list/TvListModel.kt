package com.hlopezg.presentation_tv.list

import com.hlopezg.presentation_tv.single.TvModel

data class TvListModel(
    val page: Int,
    val items: List<TvModel> = listOf()
)