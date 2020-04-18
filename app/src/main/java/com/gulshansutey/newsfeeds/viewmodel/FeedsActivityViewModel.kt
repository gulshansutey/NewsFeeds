package com.gulshansutey.newsfeeds.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gulshansutey.newsfeeds.model.FactsResponseModel
import com.gulshansutey.newsfeeds.networking.RestClient
import com.gulshansutey.newsfeeds.networking.callFeedsApi
import com.gulshansutey.newsfeeds.utils.SingleLiveEvent

/**
 * The [ViewModel] class is designed to store and manage UI-related data in a lifecycle conscious way,
 * and allows data to survive configuration changes such as screen rotations.
 * */
class FeedsActivityViewModel : ViewModel() {

    val networkErrors = SingleLiveEvent<String>()
    val isInProgress = MutableLiveData<Boolean>()
    val response = MutableLiveData<FactsResponseModel>()
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