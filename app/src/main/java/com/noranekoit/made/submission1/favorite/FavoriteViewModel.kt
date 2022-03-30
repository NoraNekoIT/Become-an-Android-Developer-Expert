package com.noranekoit.made.submission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase):ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}