package com.noranekoit.made.submission1.core.data.source.remote.network

import com.noranekoit.made.submission1.BuildConfig
import com.noranekoit.made.submission1.BuildConfig.BASE_URL_TMDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val loggingInterceptor =
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                client.addInterceptor(loggingInterceptor)
                    .build()
            }
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}