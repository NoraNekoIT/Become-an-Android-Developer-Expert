package com.noranekoit.made.submission1.core.di

import android.content.Context
import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.data.source.local.LocalDataSource
import com.noranekoit.made.submission1.core.data.source.local.room.MovieDatabase
import com.noranekoit.made.submission1.core.data.source.remote.RemoteDataSource
import com.noranekoit.made.submission1.core.data.source.remote.network.ApiConfig
import com.noranekoit.made.submission1.core.domain.repository.IMovieRepository
import com.noranekoit.made.submission1.core.domain.usecase.MovieInteractor
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase
import com.noranekoit.made.submission1.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase{
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}