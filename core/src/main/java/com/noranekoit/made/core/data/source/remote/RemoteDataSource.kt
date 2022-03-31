package com.noranekoit.made.core.data.source.remote

import android.util.Log
import com.noranekoit.made.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.core.data.source.remote.network.ApiService
import com.noranekoit.made.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun getMoviePopularAll(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMoviePopularAll()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) emit(ApiResponse.Success(dataArray))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "$e")
            }
        }.flowOn(Dispatchers.IO)
    }
}