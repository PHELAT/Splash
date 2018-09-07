package com.phelat.splash.data.mapper

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.entity.UserEntity
import com.phelat.splash.data.response.PhotosResponse

class PhotoResponseToPhotoEntity : Mapper<PhotosResponse, PhotoEntity> {

    override fun mapFromTo(from: PhotosResponse): PhotoEntity {
        return PhotoEntity(
                from.id,
                from.createdAt,
                from.updatedAt,
                from.width,
                from.height,
                from.color,
                from.likes,
                from.description,
                from.user?.id,
                from.user?.name,
                from.user?.profileImage,
                from.urls
        )
    }

    override fun mapToFrom(to: PhotoEntity): PhotosResponse {
        return PhotosResponse(
                to.id,
                to.createdAt,
                to.updatedAt,
                to.width,
                to.height,
                to.color,
                to.likes,
                to.description,
                UserEntity(
                        to.userId,
                        "",
                        to.usersName,
                        "",
                        "",
                        "",
                        -1,
                        -1,
                        -1,
                        "",
                        "",
                        to.userProfileImage
                ),
                to.photoUrls
        )
    }

}