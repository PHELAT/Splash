package com.phelat.splash.presentation.provider

import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.presentation.entity.ParcelPhotoEntity

class EmptyParcelPhotoEntityProvider : Provider<ParcelPhotoEntity> {

    override fun provide(): ParcelPhotoEntity {
        return ParcelPhotoEntity(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        )
    }

}