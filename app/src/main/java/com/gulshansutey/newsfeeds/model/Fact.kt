package com.gulshansutey.newsfeeds.model

import com.google.gson.annotations.SerializedName


data class Fact(
    val title: String?,
    val description: String? = null,
    @SerializedName("imageHref")
    val imageUrl: String? = null
)

