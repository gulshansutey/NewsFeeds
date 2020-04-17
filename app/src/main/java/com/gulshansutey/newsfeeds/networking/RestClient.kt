package com.gulshansutey.newsfeeds.networking

import com.gulshansutey.newsfeeds.BuildConfig
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
            .build()

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
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }
}
