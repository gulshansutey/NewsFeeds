package com.gulshansutey.newsfeeds.networking

import com.gulshansutey.newsfeeds.model.FactsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


/**
 * Every method must have an HTTP annotation that provides the request method and relative URL.
 * There are eight built-in annotations: HTTP, GET, POST, PUT, PATCH, DELETE, OPTIONS and HEAD.
 * The relative URL of the resource is specified in the annotation.
 * */
interface ApiInterface {


    /**
     * @param url takes a [String] api url
     *
     * @return [Call] from the created ApiInterface instance a synchronous or asynchronous
     * HTTP request to the remote webserver.
     *
     * */
    @GET
    fun getFeedsApi(@Url url: String): Call<FactsResponseModel>
}