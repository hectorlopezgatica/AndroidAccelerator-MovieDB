package com.hlopezg.data_remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header(
                "Authorization",
                "Bearer ${BuildConfig.API_SECRET}"
            )
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}