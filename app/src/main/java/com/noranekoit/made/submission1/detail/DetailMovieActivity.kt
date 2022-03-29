package com.noranekoit.made.submission1.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.noranekoit.made.submission1.BuildConfig
import com.noranekoit.made.submission1.R
import com.noranekoit.made.submission1.core.data.source.local.entity.MovieEntity
import com.noranekoit.made.submission1.core.ui.ViewModelFactory
import com.noranekoit.made.submission1.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA ="extra_data"
    }
    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private lateinit var binding: ActivityDetailMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailMovieViewModel = ViewModelProvider(this, factory)[DetailMovieViewModel::class.java]

        val detailMovie = intent.getParcelableExtra<MovieEntity>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: MovieEntity?){
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
                detailMovieViewModel.setFavoriteMovie(detailMovie,statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if (statusFavorite){
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        }else{
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}