package com.noranekoit.made.submission1.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.noranekoit.made.submission1.core.data.MovieRepository
import com.noranekoit.made.submission1.core.di.Injection
import com.noranekoit.made.submission1.core.domain.usecase.MovieUseCase
import com.noranekoit.made.submission1.detail.DetailMovieActivity
import com.noranekoit.made.submission1.detail.DetailMovieViewModel
import com.noranekoit.made.submission1.favorite.FavoriteViewModel
import com.noranekoit.made.submission1.home.HomeViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {
        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance(context: Context): ViewModelFactory =
                instance
                    ?: synchronized(this){
                        instance
                            ?:ViewModelFactory(
                                Injection.provideMovieUseCase(
                                    context
                                )
                            )
                    }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T: ViewModel> create(modelClass: Class<T>):T =
        when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(movieUseCase)as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java)->{
                DetailMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java)->{
                FavoriteViewModel(movieUseCase) as T
            }
            else -> throw  Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}