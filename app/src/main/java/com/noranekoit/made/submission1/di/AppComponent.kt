package com.noranekoit.made.submission1.di
import com.noranekoit.made.core.di.CoreComponent
import com.noranekoit.made.submission1.detail.DetailMovieActivity
import com.noranekoit.made.submission1.favorite.FavoriteFragment
import com.noranekoit.made.submission1.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class,ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailMovieActivity)
}