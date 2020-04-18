package com.gulshansutey.newsfeeds.model

data class FeedResponseModel(
    val title: String? = null,
    var rows: List<Fact> = emptyList()
)