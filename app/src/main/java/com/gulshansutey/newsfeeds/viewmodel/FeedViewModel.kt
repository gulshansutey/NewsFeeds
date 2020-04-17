package com.gulshansutey.newsfeeds.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulshansutey.newsfeeds.model.FeedResponseModel
import com.gulshansutey.newsfeeds.networking.RestClient
import com.gulshansutey.newsfeeds.networking.callFeedsApi

class FeedViewModel : ViewModel() {


    val networkErrors = MutableLiveData<String>()
    val isInProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<FeedResponseModel>()

    init {
        isInProgress.value = false
    }

    fun getFeeds() {
        isInProgress.postValue(true)
        callFeedsApi(RestClient.create(), "/s/2iodh4vg0eortkl/facts.json", onError = {
            isInProgress.postValue(false)
            networkErrors.postValue(it)
        }, onSuccess = {
            isInProgress.postValue(false)
            response.postValue(it)
        })
    }


}