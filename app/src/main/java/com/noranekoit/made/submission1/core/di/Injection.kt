package com.noranekoit.made.submission1.core.di

import android.content.Context
import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.data.source.local.LocalDataSource
import com.noranekoit.made.submission1.core.data.source.local.room.MovieDatabase
import com.noranekoit.made.submission1.core.data.source.remote.RemoteDataSource
import com.noranekoit.made.submission1.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}