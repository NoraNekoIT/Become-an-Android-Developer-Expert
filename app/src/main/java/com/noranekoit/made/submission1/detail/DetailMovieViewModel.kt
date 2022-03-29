package com.noranekoit.made.submission1.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.domain.model.Moviem

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun setFavoriteMovie(movie: Moviem, newStatus: Boolean) =
        movieRepository.setFavoriteMovie(movie, newStatus)
}