package com.phelat.splash.di

import android.app.Application
import android.content.Context
import com.jakewharton.picasso.OkHttp3Downloader
import com.phelat.splash.BuildConfig
import com.phelat.splash.di.scopes.ForApplication
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient

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
    @ForApplication
    fun provideOkHttpDownloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    @Provides
    @ForApplication
    fun providePicasso(
            context: Context,
            okHttp3Downloader: OkHttp3Downloader
    ): Picasso {
        return Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .loggingEnabled(BuildConfig.DEBUG)
                .build()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

}