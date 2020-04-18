package com.gulshansutey.newsfeeds.model

data class FactsResponseModel(
    val title: String? = null,
    var rows: List<Fact> = emptyList()
)