package com.phelat.splash.remote.di

import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.di.modules.ApiModule
import com.phelat.splash.remote.di.scopes.ForNetwork
import dagger.Component
import okhttp3.OkHttpClient

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Component(modules = [NetworkModule::class, ApiModule::class])
@ForNetwork
interface NetworkComponent {

    fun okHttpClient(): OkHttpClient

    fun photosAPI(): PhotosAPI

}