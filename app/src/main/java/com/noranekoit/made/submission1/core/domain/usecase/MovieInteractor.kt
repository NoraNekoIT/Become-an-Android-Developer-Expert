package com.noranekoit.made.submission1.core.domain.usecase

import com.noranekoit.made.submission1.core.domain.model.Moviem
import com.noranekoit.made.submission1.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getMoviePopularAll() = movieRepository.getMoviePopularAll()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Moviem, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)


}