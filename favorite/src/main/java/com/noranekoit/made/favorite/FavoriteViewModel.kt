package com.noranekoit.made.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noranekoit.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}