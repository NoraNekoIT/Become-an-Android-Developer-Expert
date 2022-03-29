package com.noranekoit.made.submission1.core.domain.usecase

import androidx.lifecycle.LiveData
import com.noranekoit.made.submission1.core.data.Resource
import com.noranekoit.made.submission1.core.domain.model.Moviem

interface MovieUseCase {
    fun getMoviePopularAll(): LiveData<Resource<List<Moviem>>>
    fun getFavoriteMovie(): LiveData<List<Moviem>>
    fun setFavoriteMovie(movie: Moviem, state: Boolean)
}