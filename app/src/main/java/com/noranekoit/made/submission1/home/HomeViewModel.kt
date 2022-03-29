package com.noranekoit.made.submission1.home

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase):ViewModel() {
    val movie = movieUseCase.getMoviePopularAll()
}