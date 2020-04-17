package com.gulshansutey.newsfeeds.model

import com.google.gson.annotations.SerializedName

data class FeedModel(
    val title: String?,
    val description: String?,
    @SerializedName("imageHref")
    val imageUrl: String?
)