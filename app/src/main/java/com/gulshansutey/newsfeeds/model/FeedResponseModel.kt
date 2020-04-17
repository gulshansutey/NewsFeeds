package com.gulshansutey.newsfeeds.model

data class FeedResponseModel(
    val title: String?,
    val rows: List<FeedModel> = emptyList()
)