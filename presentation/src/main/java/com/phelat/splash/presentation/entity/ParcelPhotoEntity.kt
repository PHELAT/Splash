package com.phelat.splash.presentation.entity

import android.os.Parcelable
import com.phelat.splash.presentation.model.ParcelPhotoUrlsData
import com.phelat.splash.presentation.model.ParcelProfileImageData
import kotlinx.android.parcel.Parcelize

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Parcelize
data class ParcelPhotoEntity(

        val id: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val width: Int?,
        val height: Int?,
        val color: String?,
        val likes: Int?,
        val description: String?,
        val userId: String?,
        val usersName: String?,
        val userProfileImage: ParcelProfileImageData?,
        val photoUrls: ParcelPhotoUrlsData?

) : Parcelable