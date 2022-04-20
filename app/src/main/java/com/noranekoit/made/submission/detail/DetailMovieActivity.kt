package com.noranekoit.made.submission.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.noranekoit.made.core.BuildConfig
import com.noranekoit.made.core.domain.model.Moviem
import com.noranekoit.made.submission.R
import com.noranekoit.made.submission.databinding.ActivityDetailMovieBinding
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    private val detailMovieViewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Moviem>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Moviem?) {
        detailMovie?.let {
            val scoreRating: Float = detailMovie.score!!.toFloat().div(2)
            supportActionBar?.title = detailMovie.title
            binding.content.apply {
                tvDetailDescription.text = detailMovie.description
                tvDetailTitle.text = detailMovie.title
                tvDetailDate.text =
                    resources.getString(R.string.date_airing, detailMovie.dateAiring)
                tvDetailRating.rating = scoreRating
            }
            Picasso.get()
                .load("${BuildConfig.BASE_URL_IMAGE}${detailMovie.imagePath}")
                .into(binding.ivDetailImage)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

}