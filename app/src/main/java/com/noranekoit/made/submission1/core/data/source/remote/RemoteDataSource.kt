package com.noranekoit.made.submission1.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiService
import com.noranekoit.made.submission1.core.data.source.remote.response.CatalogueResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource private constructor(private val apiService: ApiService) {
    fun getMoviePopularAll(): LiveData<ApiResponse<List<MovieResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        val client = apiService.getMoviePopularAll()
        client.enqueue(object : Callback<CatalogueResponse> {
            override fun onResponse(
                call: Call<CatalogueResponse>,
                response: Response<CatalogueResponse>
            ) {
                val dataArray = response.body()?.results
                resultData.value =
                    if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty

            }

            override fun onFailure(call: Call<CatalogueResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "${t.message}")
                resultData.postValue(
                    ApiResponse.Error(
                        t.message.toString()
                    )
                )
            }
        })
        return resultData
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service).apply {
                    instance = this
                }
            }

    }
}