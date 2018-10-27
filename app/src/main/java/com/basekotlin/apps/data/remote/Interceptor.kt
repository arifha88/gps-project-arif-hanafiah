package com.basekotlin.apps.data.remote

import okhttp3.Interceptor

import okhttp3.Response

class Interceptor : okhttp3.Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val original = chain!!.request()
        val method = original.method()

        val requestBuilder = original.newBuilder()
                .header("Accept", "application/json")
                .method(method, original.body())

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}

