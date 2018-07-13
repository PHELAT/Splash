package com.phelat.splash.presentation.mapper

import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.model.ProfileImageData
import com.phelat.splash.presentation.model.ParcelProfileImageData

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class ProfileImageDataToParcelProfileImageData : Mapper<ProfileImageData?, ParcelProfileImageData?> {

    override fun mapFromTo(from: ProfileImageData?): ParcelProfileImageData? {
        return from?.let { nonNullFrom ->
            ParcelProfileImageData(nonNullFrom.small, nonNullFrom.medium, nonNullFrom.large)
        }
    }

    override fun mapToFrom(to: ParcelProfileImageData?): ProfileImageData? {
        return to?.let { nonNullTo ->
            ProfileImageData(nonNullTo.small, nonNullTo.medium, nonNullTo.large)
        }
    }

}