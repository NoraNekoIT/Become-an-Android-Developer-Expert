package com.noranekoit.made.submission1.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.domain.model.Moviem
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Moviem, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}