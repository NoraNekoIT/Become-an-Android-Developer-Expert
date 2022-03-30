package com.noranekoit.made.submission1

import android.app.Application
import com.noranekoit.made.core.di.DaggerCoreComponent
import com.noranekoit.made.submission1.di.AppComponent
import com.noranekoit.made.submission1.di.DaggerAppComponent
open class MyApplication: Application() {
    private val coreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}