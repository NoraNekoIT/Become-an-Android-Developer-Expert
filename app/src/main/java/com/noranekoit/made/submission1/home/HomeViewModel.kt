package com.noranekoit.made.submission1.home

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.data.MovieRepository

class HomeViewModel(movieRepository: MovieRepository):ViewModel() {
    val movie = movieRepository.getMoviePopularAll()
}