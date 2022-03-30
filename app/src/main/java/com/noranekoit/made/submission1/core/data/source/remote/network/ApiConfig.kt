package com.noranekoit.made.submission1.core.data.source.remote.network

import com.noranekoit.made.submission1.BuildConfig
import com.noranekoit.made.submission1.BuildConfig.BASE_URL_TMDB
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

        private fun provideOkHttpClient(): OkHttpClient{
            if (BuildConfig.DEBUG) {
                return OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            }
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
        }

        fun provideApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_TMDB)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
            return retrofit.create(ApiService::class.java)
        }

}