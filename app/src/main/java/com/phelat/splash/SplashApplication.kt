package com.phelat.splash

import android.app.Application
import com.phelat.splash.di.DaggerSplashComponent
import com.phelat.splash.di.SplashComponent
import com.phelat.splash.remote.di.DaggerNetworkComponent
import com.phelat.splash.remote.di.NetworkComponent
import com.phelat.splash.remote.di.NetworkModule

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class SplashApplication : Application() {

    lateinit var networkComponent: NetworkComponent

    lateinit var splashComponent: SplashComponent

    override fun onCreate() {
        super.onCreate()

        networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule(BuildConfig.BASE_URL))
                .build()

        splashComponent = DaggerSplashComponent.builder()
                .networkComponent(networkComponent)
                .build()
    }

}