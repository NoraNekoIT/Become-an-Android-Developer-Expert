package com.noranekoit.made.submission1.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiService
import com.noranekoit.made.submission1.core.data.source.remote.response.CatalogueResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class RemoteDataSource private constructor(private val apiService: ApiService) {
    suspend fun getMoviePopularAll(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviePopularAll()
                val dataArray = response.results
                    if (dataArray.isNotEmpty()) emit(ApiResponse.Success(dataArray))
                    else emit(ApiResponse.Empty)
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "$e")
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }

    }
}