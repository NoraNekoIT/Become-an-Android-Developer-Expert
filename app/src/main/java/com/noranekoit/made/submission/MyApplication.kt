package com.noranekoit.made.submission

import android.app.Application
import com.noranekoit.made.core.di.databaseModule
import com.noranekoit.made.core.di.networkModule
import com.noranekoit.made.core.di.repositoryModule
import com.noranekoit.made.submission.di.useCaseModule
import com.noranekoit.made.submission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}