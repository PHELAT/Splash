package com.phelat.splash.remote.di

import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.di.modules.ApiModule
import com.phelat.splash.remote.di.scopes.ForNetwork
import dagger.Component
import okhttp3.OkHttpClient

@Component(modules = [NetworkModule::class, ApiModule::class])
@ForNetwork
interface NetworkComponent {

    fun okHttpClient(): OkHttpClient

    fun photosAPI(): PhotosAPI

}