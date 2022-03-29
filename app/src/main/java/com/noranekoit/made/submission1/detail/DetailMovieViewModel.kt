package com.noranekoit.made.submission1.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun setFavoriteMovie(movie: MovieEntity, newStatus: Boolean) =
        movieRepository.setFavoriteMovie(movie, newStatus)
}