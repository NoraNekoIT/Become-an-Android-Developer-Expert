package com.noranekoit.made.core.utils

import com.noranekoit.made.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.core.data.source.remote.response.MovieResponse
import com.noranekoit.made.core.domain.model.Moviem

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

    fun mapEntitiesToDomain(input: List<MovieEntity>):List<Moviem> =
        input.map {
            Moviem(
                id = it.id,
                title = it.title,
                description = it.description,
                dateAiring = it.dateAiring,
                score = it.score,
                imagePath = it.imagePath,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Moviem) =
        MovieEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            dateAiring = input.dateAiring,
            score = input.score,
            imagePath = input.imagePath,
            isFavorite = input.isFavorite
        )
}