package com.gulshansutey.newsfeeds.networking

import com.gulshansutey.newsfeeds.model.FactsResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Defined are the network calls that are being used in project.
 * */
fun callFactsApi(
    client: ApiInterface,
    url: String, onSuccess: (response: FactsResponseModel) -> Unit,
    onError: (error: String) -> Unit
) {

    client.getFeedsApi(url).enqueue(object :
        Callback<FactsResponseModel> {
        override fun onFailure(call: Call<FactsResponseModel>, t: Throwable) {
            t.printStackTrace()
            onError(t.localizedMessage ?: "Unknown error")
        }

        override fun onResponse(
            call: Call<FactsResponseModel>,
            response: Response<FactsResponseModel>
        ) {
            if (response.isSuccessful) {
                onSuccess(response.body() ?: FactsResponseModel("Facts"))
            } else {
                onError(response.errorBody()?.string() ?: "Unknown error")
            }
        }
    })

}