package com.noranekoit.made.core.data.source.local

import com.noranekoit.made.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource constructor(private val movieDao: MovieDao) {

    fun getAllMoviePopular(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }

}