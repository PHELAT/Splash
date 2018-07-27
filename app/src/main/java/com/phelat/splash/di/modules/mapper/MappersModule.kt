package com.phelat.splash.di.modules.mapper

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.model.PhotoUrlsData
import com.phelat.splash.data.model.ProfileImageData
import com.phelat.splash.di.scopes.ForActivity
import com.phelat.splash.presentation.entity.ParcelPhotoEntity
import com.phelat.splash.presentation.mapper.PhotoEntityToParcelPhotoEntity
import com.phelat.splash.presentation.mapper.PhotoUrlsDataToParcelPhotoUrlsData
import com.phelat.splash.presentation.mapper.ProfileImageDataToParcelProfileImageData
import com.phelat.splash.presentation.model.ParcelPhotoUrlsData
import com.phelat.splash.presentation.model.ParcelProfileImageData
import dagger.Module
import dagger.Provides

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class MappersModule {

    @Provides
    @ForActivity
    fun provideProfileImageDataToParcelProfileImageData(): Mapper<ProfileImageData?, ParcelProfileImageData?> {
        return ProfileImageDataToParcelProfileImageData()
    }

    @Provides
    @ForActivity
    fun providePhotoUrlsDataToParcelPhotoUrlsData(): Mapper<PhotoUrlsData?, ParcelPhotoUrlsData?> {
        return PhotoUrlsDataToParcelPhotoUrlsData()
    }

    @Provides
    @ForActivity
    fun providePhotoEntityToParcelPhotoEntity(
            photoUrlsMapper: Mapper<PhotoUrlsData?, ParcelPhotoUrlsData?>,
            profileImageDataMapper: Mapper<ProfileImageData?, ParcelProfileImageData?>
    ): Mapper<PhotoEntity, ParcelPhotoEntity> {
        return PhotoEntityToParcelPhotoEntity(photoUrlsMapper, profileImageDataMapper)
    }

}