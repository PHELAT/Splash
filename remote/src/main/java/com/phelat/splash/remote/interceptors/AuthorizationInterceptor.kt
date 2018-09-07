package com.phelat.splash.remote.interceptors

import com.phelat.splash.remote.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Client-ID ${BuildConfig.CLIEND_ID}")
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}