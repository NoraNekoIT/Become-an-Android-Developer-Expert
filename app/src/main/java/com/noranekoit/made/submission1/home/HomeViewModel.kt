package com.noranekoit.made.submission1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getMoviePopularAll().asLiveData()
}