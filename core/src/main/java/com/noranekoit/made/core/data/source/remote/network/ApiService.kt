package com.noranekoit.made.core.data.source.remote.network
import com.noranekoit.made.core.data.source.remote.response.CatalogueResponse
import com.noranekoit.made.core.BuildConfig.TMDB_KEY as ApiKeyTMDB
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMoviePopularAll(
        @Query("api_key") apiKey: String = ApiKeyTMDB
    ): CatalogueResponse
}