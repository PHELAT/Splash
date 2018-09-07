package com.phelat.splash.data.provider

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.provider.base.Provider

class EmptyPhotoEntityProvider : Provider<PhotoEntity> {

    override fun provide(): PhotoEntity {
        return PhotoEntity(
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