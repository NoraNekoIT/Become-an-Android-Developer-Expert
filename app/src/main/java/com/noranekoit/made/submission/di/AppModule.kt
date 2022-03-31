package com.noranekoit.made.submission.di

import com.noranekoit.made.core.domain.usecase.MovieInteractor
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import com.noranekoit.made.submission.detail.DetailMovieViewModel
import com.noranekoit.made.submission.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> {
        MovieInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}