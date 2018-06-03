package com.phelat.splash.remote.entity

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

data class User(

        val id: String,
        val username: String,
        val name: String,
        val portfolioUrl: String,
        val bio: String,
        val location: String,
        val totalLikes: Int,
        val totalPhotos: Int,
        val totalCollections: Int,
        val instagramUsername: String,
        val twitterUsername: String,
        val profileImage: ProfileImage

)