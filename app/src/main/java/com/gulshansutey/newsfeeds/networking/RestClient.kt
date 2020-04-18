package com.gulshansutey.newsfeeds.networking

import com.google.gson.GsonBuilder
import com.gulshansutey.newsfeeds.BuildConfig
import com.gulshansutey.newsfeeds.model.FeedResponseModel
import com.gulshansutey.newsfeeds.utils.NetworkConnectionInterceptor
import com.gulshansutey.newsfeeds.utils.NullObjectRemoverDeserializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Each Call from the ApiInterface can make a synchronous or asynchronous HTTP request to the remote webserver.
 * The RestClient class generates an implementation of the ApiInterface interface.
 * */
object RestClient {

    /**
     * @param baseUrl The API base URL, if empty default value of [BuildConfig.BASE_URL] will be used.
     * @return The instance of [ApiInterface]
     * */

    fun create(baseUrl: String = BuildConfig.BASE_URL): ApiInterface {

        /**
         * Basic [HttpLoggingInterceptor.Level.BODY] level interceptor for logging full
         * content of request and response
         * */
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(NetworkConnectionInterceptor())
            .build()

        /**
         * A custom deserializer to ignore null objects to being added in the array.
         * */
        val factory = GsonBuilder()
            .registerTypeAdapter(FeedResponseModel::class.java, NullObjectRemoverDeserializer())
            .create()
        /**
         * The Retrofit class here generates an implementation of the ApiInterface interface.
         * Retrofit adapts an interface to HTTP calls by using annotations on the declared methods to
         * define how requests are made.
         *
         * [GsonConverterFactory] A converter factory used for Decoding and Encoding JSON.
         * */
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .build()
            .create(ApiInterface::class.java)
    }
}
