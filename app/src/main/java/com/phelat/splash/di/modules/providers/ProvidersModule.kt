package com.phelat.splash.di.modules.providers

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.provider.EmptyPhotoEntityProvider
import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.di.scopes.ForActivity
import com.phelat.splash.presentation.entity.ParcelPhotoEntity
import com.phelat.splash.presentation.provider.EmptyParcelPhotoEntityProvider
import com.phelat.splash.remote.provider.SigProvider
import dagger.Module
import dagger.Provides

/**
 * Created by MAHDi on 6/27/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class ProvidersModule {

    @Provides
    @ForActivity
    fun provideEmptyPhotoEntityProvider(): Provider<PhotoEntity> {
        return EmptyPhotoEntityProvider()
    }

    @Provides
    @ForActivity
    fun provideEmptyParcelPhotoEntityProvider(): Provider<ParcelPhotoEntity> {
        return EmptyParcelPhotoEntityProvider()
    }

    @Provides
    @ForActivity
    fun provideSigProvider(): Provider<Long> {
        return SigProvider()
    }

}