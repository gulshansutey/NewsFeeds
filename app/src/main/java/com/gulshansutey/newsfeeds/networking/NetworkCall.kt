package com.gulshansutey.newsfeeds.networking

import com.gulshansutey.newsfeeds.model.FeedResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Defined are the network calls that are being used in project.
 * */
fun callFeedsApi(
    client: ApiInterface,
    url: String, onSuccess: (response: FeedResponseModel) -> Unit,
    onError: (error: String) -> Unit
) {

    client.getFeedsApi(url).enqueue(object :
        Callback<FeedResponseModel> {
        override fun onFailure(call: Call<FeedResponseModel>, t: Throwable) {
            t.printStackTrace()
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