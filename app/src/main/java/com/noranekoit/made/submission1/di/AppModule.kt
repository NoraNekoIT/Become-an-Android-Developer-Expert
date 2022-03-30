package com.noranekoit.made.submission1.di

import com.noranekoit.made.core.domain.usecase.MovieInteractor
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase
}