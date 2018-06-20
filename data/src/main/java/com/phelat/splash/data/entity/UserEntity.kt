package com.phelat.splash.data.entity

import com.phelat.splash.data.model.ProfileImageData

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class UserEntity(

        val id: String?,
        val username: String?,
        val name: String?,
        val portfolioUrl: String?,
        val bio: String?,
        val location: String?,
        val totalLikes: Int?,
        val totalPhotos: Int?,
        val totalCollections: Int?,
        val instagramUsername: String?,
        val twitterUsername: String?,
        val profileImage: ProfileImageData?

)