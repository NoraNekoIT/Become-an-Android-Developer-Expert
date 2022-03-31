package com.noranekoit.made.core.domain.usecase

import com.noranekoit.made.core.domain.model.Moviem
import com.noranekoit.made.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getMoviePopularAll() = movieRepository.getMoviePopularAll()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Moviem, state: Boolean) =
        movieRepository.setFavoriteMovie(movie, state)

}