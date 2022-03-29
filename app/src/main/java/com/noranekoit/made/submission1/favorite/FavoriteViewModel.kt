package com.noranekoit.made.submission1.favorite

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.data.MovieRepository

class FavoriteViewModel(movieRepository: MovieRepository):ViewModel() {
    val favoriteMovie = movieRepository.getFavoriteMovie()
}