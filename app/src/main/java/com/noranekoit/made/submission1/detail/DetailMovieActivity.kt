package com.noranekoit.made.submission1.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.noranekoit.made.core.domain.model.Moviem
import com.noranekoit.made.core.ui.ViewModelFactory
import com.noranekoit.made.submission1.BuildConfig
import com.noranekoit.made.submission1.MyApplication
import com.noranekoit.made.submission1.R
import com.noranekoit.made.submission1.databinding.ActivityDetailMovieBinding
import javax.inject.Inject

class DetailMovieActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private val detailMovieViewModel: DetailMovieViewModel by viewModels {
        factory
    }
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Moviem>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Moviem?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.tvDetailDescription.text = detailMovie.description
            Glide.with(this@DetailMovieActivity)
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
}