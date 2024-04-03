package com.hlopezg.data_remote

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkNDFkNDk3YzE0ZWFkMzUzYzhkOTMyOGRmYjIwNjRjYiIsInN1YiI6IjVlNzAyNWNmY2VkYWM0MDAxNzRmMjVjZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.dpAM_HabsEMI38lB_aPW5d5Kd5wfSaZDrn-8svWa0VM")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}