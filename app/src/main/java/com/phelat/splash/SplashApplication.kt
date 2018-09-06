package com.phelat.splash

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.phelat.splash.di.DaggerSplashComponent
import com.phelat.splash.di.SplashComponent
import com.phelat.splash.di.SplashModule
import com.phelat.splash.remote.di.DaggerNetworkComponent
import com.phelat.splash.remote.di.NetworkComponent
import com.phelat.splash.remote.di.NetworkModule
import com.squareup.picasso.Picasso
import javax.inject.Inject

class SplashApplication : Application() {

    @Inject
    lateinit var picasso: Picasso

    lateinit var networkComponent: NetworkComponent

    lateinit var splashComponent: SplashComponent

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(BuildConfig.BASE_URL))
                .build()

        splashComponent = DaggerSplashComponent.builder()
                .networkComponent(networkComponent)
                .splashModule(SplashModule(this))
                .build()

        splashComponent.inject(this)

        Picasso.setSingletonInstance(picasso)
    }

}