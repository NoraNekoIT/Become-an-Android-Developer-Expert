package com.noranekoit.made.submission1.core.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiConfig
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiService
import com.noranekoit.made.submission1.core.data.source.remote.response.CatalogueResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class RemoteDataSource private constructor() {
    fun getMoviePopularAll(): LiveData<ApiResponse<List<MovieResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        ApiConfig.getApiService().getMoviePopularAll()
            .enqueue(object : Callback<CatalogueResponse<MovieResponse>> {
                override fun onResponse(
                    call: Call<CatalogueResponse<MovieResponse>>,
                    response: Response<CatalogueResponse<MovieResponse>>
                ) {
                    if (response.isSuccessful) {
                        resultData.postValue(ApiResponse.Success(response.body()?.results as List<MovieResponse>))
                    }
                }

                override fun onFailure(call: Call<CatalogueResponse<MovieResponse>>, t: Throwable) {
                    Log.e("Failure", "${t.message}")
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

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply {
                    instance = this
                }
            }

    }
}