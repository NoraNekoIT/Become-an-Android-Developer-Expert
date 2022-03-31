package com.noranekoit.made.core.domain.usecase

import com.noranekoit.made.core.data.Resource
import com.noranekoit.made.core.domain.model.Moviem
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getMoviePopularAll(): Flow<Resource<List<Moviem>>>
    fun getFavoriteMovie(): Flow<List<Moviem>>
    fun setFavoriteMovie(movie: Moviem, state: Boolean)
}