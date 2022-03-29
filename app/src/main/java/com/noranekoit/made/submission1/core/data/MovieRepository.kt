package com.noranekoit.made.submission1.core.data

import androidx.lifecycle.LiveData
import com.noranekoit.made.submission1.core.data.source.local.LocalDataSource
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.submission1.core.data.source.remote.RemoteDataSource
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import com.noranekoit.made.submission1.core.utils.AppExecutors
import com.noranekoit.made.submission1.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository = Companion.instance ?: synchronized(this) {
            instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    fun getMoviePopularAll(): LiveData<Resource<List<MovieEntity>>> =
    object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutors){
        override fun loadFromDB(): LiveData<List<MovieEntity>> {
            return localDataSource.getAllMovie()
        }

        override fun shouldFetch(data: List<MovieEntity>?): Boolean =
            data == null || data.isEmpty()


        override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
            remoteDataSource.getMoviePopularAll()


        override fun saveCallResult(data: List<MovieResponse>) {
            val movieList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertMovie(movieList)
        }

    }.asLiveData()

    fun getFavoriteMovie() : LiveData<List<MovieEntity>>{
        return localDataSource.getFavoriteMovie()
    }

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean){
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteMovie(movie,state)
        }
    }
}