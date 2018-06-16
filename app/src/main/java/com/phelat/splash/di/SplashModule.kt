package com.phelat.splash.di

import android.app.Application
import android.content.Context
import com.phelat.splash.di.scopes.ForApplication
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class SplashModule(private val application: Application) {

    @Provides
    @ForApplication
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @ForApplication
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}