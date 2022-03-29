package com.noranekoit.made.submission1.core.utils

import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.submission1.core.data.source.remote.response.MovieResponse

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id.toString(),
                title = it.title,
                description = it.overview,
                dateAiring = it.releaseDate,
                score = it.voteAverage.toString(),
                imagePath = it.posterPath,
                isFavorite = false
                )
            movieList.add(movie)
        }
        return movieList
    }
}