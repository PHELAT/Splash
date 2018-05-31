package com.phelat.splash.remote.response

import com.phelat.splash.remote.entity.Urls
import com.phelat.splash.remote.entity.User

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class PhotosResponse(

        val id: String,
        val createdAt: String,
        val updatedAt: String,
        val width: Int,
        val height: Int,
        val color: String,
        val likes: Int,
        val description: String,
        val user: User,
        val urls: Urls

)