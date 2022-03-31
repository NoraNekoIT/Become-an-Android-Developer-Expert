package com.noranekoit.made.core.data

import com.noranekoit.made.core.data.source.local.LocalDataSource
import com.noranekoit.made.core.data.source.remote.RemoteDataSource
import com.noranekoit.made.core.data.source.remote.network.ApiResponse
import com.noranekoit.made.core.data.source.remote.response.MovieResponse
import com.noranekoit.made.core.domain.model.Moviem
import com.noranekoit.made.core.domain.repository.IMovieRepository
import com.noranekoit.made.core.utils.AppExecutors
import com.noranekoit.made.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getMoviePopularAll(): Flow<Resource<List<Moviem>>> =
        object : NetworkBoundResource<List<Moviem>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Moviem>> {
                return localDataSource.getAllMoviePopular().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Moviem>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMoviePopularAll()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Moviem>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Moviem, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }

}
