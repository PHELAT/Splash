package com.phelat.splash.data.entity

import com.phelat.splash.data.model.PhotoUrlsData
import com.phelat.splash.data.model.ProfileImageData

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class PhotoEntity(

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
        val userProfileImage: ProfileImageData?,
        val photoUrls: PhotoUrlsData?

)