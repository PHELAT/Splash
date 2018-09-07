package com.phelat.splash.remote.di.modules

import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.di.scopes.ForNetwork
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @ForNetwork
    fun providePhotosApi(retrofit: Retrofit): PhotosAPI {
        return retrofit.create(PhotosAPI::class.java)
    }

}