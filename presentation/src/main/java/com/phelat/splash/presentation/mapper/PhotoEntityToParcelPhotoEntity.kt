package com.phelat.splash.presentation.mapper

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.model.PhotoUrlsData
import com.phelat.splash.data.model.ProfileImageData
import com.phelat.splash.presentation.entity.ParcelPhotoEntity
import com.phelat.splash.presentation.model.ParcelPhotoUrlsData
import com.phelat.splash.presentation.model.ParcelProfileImageData

/**
 * Created by MAHDi on 7/13/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoEntityToParcelPhotoEntity(

        private val photoUrlsDataMapper: Mapper<PhotoUrlsData?, ParcelPhotoUrlsData?>,
        private val profileImageDataMapper: Mapper<ProfileImageData?, ParcelProfileImageData?>

) : Mapper<PhotoEntity, ParcelPhotoEntity> {

    override fun mapFromTo(from: PhotoEntity): ParcelPhotoEntity {
        return ParcelPhotoEntity(
                from.id,
                from.createdAt,
                from.updatedAt,
                from.width,
                from.height,
                from.color,
                from.likes,
                from.description,
                from.userId,
                from.usersName,
                profileImageDataMapper.mapFromTo(from.userProfileImage),
                photoUrlsDataMapper.mapFromTo(from.photoUrls)
        )
    }

    override fun mapToFrom(to: ParcelPhotoEntity): PhotoEntity {
        return PhotoEntity(
                to.id,
                to.createdAt,
                to.updatedAt,
                to.width,
                to.height,
                to.color,
                to.likes,
                to.description,
                to.userId,
                to.usersName,
                profileImageDataMapper.mapToFrom(to.userProfileImage),
                photoUrlsDataMapper.mapToFrom(to.photoUrls)
        )
    }

}