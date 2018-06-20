package com.phelat.splash.data.response

import com.phelat.splash.data.entity.UserEntity
import com.phelat.splash.data.model.PhotoUrlsData

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class PhotosResponse(

        val id: String?,
        val createdAt: String?,
        val updatedAt: String?,
        val width: Int?,
        val height: Int?,
        val color: String?,
        val likes: Int?,
        val description: String?,
        val user: UserEntity?,
        val urls: PhotoUrlsData?

)