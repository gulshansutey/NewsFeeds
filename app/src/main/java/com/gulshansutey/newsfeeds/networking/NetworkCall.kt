package com.gulshansutey.newsfeeds.networking

import com.gulshansutey.newsfeeds.model.FeedResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Defined are the network calls that are being used in project.
 * */

fun getFeedsFromUrl(
    client: RestClient,
    url: String, onSuccess: (response: FeedResponseModel) -> Unit,
    onError: (error: String) -> Unit
) {

    client.create().getFeeds(url).enqueue(object :
        Callback<FeedResponseModel> {
        override fun onFailure(call: Call<FeedResponseModel>, t: Throwable) {
            onError(t.localizedMessage ?: "Unknown error")
        }

        override fun onResponse(
            call: Call<FeedResponseModel>,
            response: Response<FeedResponseModel>
        ) {
            if (response.isSuccessful) {
                onSuccess(response.body() ?: FeedResponseModel("Facts"))
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
    })


}