package com.example.chapter14

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.github.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization","Bearer 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")
                .build()
            it.proceed(request)
        }
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // internet 권한 허용하기
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}