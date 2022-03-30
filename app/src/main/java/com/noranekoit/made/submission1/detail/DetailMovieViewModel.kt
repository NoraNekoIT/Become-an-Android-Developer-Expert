package com.noranekoit.made.submission1.detail

import androidx.lifecycle.ViewModel
import com.noranekoit.made.core.domain.usecase.MovieUseCase
import com.noranekoit.made.core.domain.model.Moviem
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Moviem, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}