package com.phelat.splash.presentation.provider

import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.presentation.entity.ParcelPhotoEntity

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class EmptyParcelPhotoEntityProvider : Provider<ParcelPhotoEntity> {

    override fun provide(): ParcelPhotoEntity {
        return ParcelPhotoEntity(null,
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
                null)
    }

}