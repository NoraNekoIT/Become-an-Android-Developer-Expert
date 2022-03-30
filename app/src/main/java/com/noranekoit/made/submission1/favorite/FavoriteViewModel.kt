package com.noranekoit.made.submission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase):ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}