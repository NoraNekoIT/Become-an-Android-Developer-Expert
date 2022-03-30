package com.noranekoit.made.submission1.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.submission1.core.domain.model.Moviem
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Moviem, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}