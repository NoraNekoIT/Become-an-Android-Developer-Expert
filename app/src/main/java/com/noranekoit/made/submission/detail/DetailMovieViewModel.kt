package com.noranekoit.made.submission.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import com.noranekoit.made.core.domain.model.Moviem

class DetailMovieViewModel(private val movieUseCase: MovieUseCase) :
    ViewModel() {
    fun setFavoriteMovie(movie: Moviem, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}