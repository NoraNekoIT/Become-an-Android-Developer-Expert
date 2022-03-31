package com.noranekoit.made.core.di

import com.noranekoit.made.core.data.MovieRepository
import com.noranekoit.made.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository
}