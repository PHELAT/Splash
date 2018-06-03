package com.phelat.splash.remote.di.modules

import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.di.scopes.ForNetwork
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class ApiModule {

    @Provides
    @ForNetwork
    fun providePhotosApi(retrofit: Retrofit): PhotosAPI {
        return retrofit.create(PhotosAPI::class.java)
    }

}