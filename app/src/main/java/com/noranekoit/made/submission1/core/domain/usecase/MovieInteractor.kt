package com.noranekoit.made.submission1.core.domain.usecase

import androidx.lifecycle.LiveData
import com.noranekoit.made.submission1.core.data.Resource
import com.noranekoit.made.submission1.core.domain.model.Moviem
import com.noranekoit.made.submission1.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getMoviePopularAll() = movieRepository.getMoviePopularAll()

    override fun getFavoriteMovie(): LiveData<List<Moviem>> = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Moviem, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)


}