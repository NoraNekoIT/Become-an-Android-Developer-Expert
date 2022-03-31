package com.noranekoit.made.submission.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noranekoit.made.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getMoviePopularAll().asLiveData()
}