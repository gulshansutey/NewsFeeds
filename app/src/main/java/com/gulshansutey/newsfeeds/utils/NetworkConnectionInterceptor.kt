package com.gulshansutey.newsfeeds.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.InetAddress

/**
 * An #okhttp3 Interceptor to check internet connectivity
 * */
class NetworkConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw IOException("Unable to connect to internet")
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    /**
     * Checks for connectivity by Determining the IP address of a host.
     * @return [Boolean] false if no IP address for the {@code host} could be found
     * */
    private fun isConnected(): Boolean {
        return try {
            val ipAddr: InetAddress = InetAddress.getByName("google.com")
            !ipAddr.equals("")
        } catch (e: Exception) {
            false
        }
    }

}