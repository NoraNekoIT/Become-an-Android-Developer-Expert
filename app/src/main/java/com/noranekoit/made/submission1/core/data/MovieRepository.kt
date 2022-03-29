package com.noranekoit.made.submission1.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.noranekoit.made.submission1.core.data.source.local.LocalDataSource
import com.noranekoit.made.submission1.core.data.source.remote.RemoteDataSource
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse
import com.noranekoit.made.submission1.core.domain.model.Moviem
import com.noranekoit.made.submission1.core.domain.repository.IMovieRepository
import com.noranekoit.made.submission1.core.utils.AppExecutors
import com.noranekoit.made.submission1.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
):IMovieRepository {
    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository = instance ?: synchronized(this) {
            instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
        }
    }

    override fun getMoviePopularAll(): LiveData<Resource<List<Moviem>>> =
    object : NetworkBoundResource<List<Moviem>, List<MovieResponse>>(appExecutors){
        override fun loadFromDB(): LiveData<List<Moviem>> {
            return Transformations.map(localDataSource.getAllMoviePopular()){
             DataMapper.mapEntitiesToDomain(it)
            }
        }

        override fun shouldFetch(data: List<Moviem>?): Boolean =
            data == null || data.isEmpty()


        override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
            remoteDataSource.getMoviePopularAll()


        override fun saveCallResult(data: List<MovieResponse>) {
            val movieList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertMovie(movieList)
        }

    }.asLiveData()

    override fun getFavoriteMovie() : LiveData<List<Moviem>>{
        return Transformations.map(localDataSource.getFavoriteMovie()){
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Moviem, state: Boolean){
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteMovie(movieEntity,state)
        }
    }
}