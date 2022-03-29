package com.noranekoit.made.submission1.core.data.source.remote.network

import com.noranekoit.made.submission1.BuildConfig.TMDB_KEY as ApiKeyTMDB
import com.noranekoit.made.submission1.core.data.source.remote.response.CatalogueResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    fun getMoviePopularAll(
        @Query("api_key") apiKey: String = ApiKeyTMDB
    ): Call<CatalogueResponse<MovieResponse>>
}