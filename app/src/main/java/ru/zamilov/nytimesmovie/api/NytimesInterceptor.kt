package ru.zamilov.nytimesmovie.api

import okhttp3.Interceptor
import okhttp3.Response
import ru.zamilov.nytimesmovie.BuildConfig

class NytimesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}