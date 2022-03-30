package com.noranekoit.made.submission1.core.di

import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class,DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository):IMovieRepository
}