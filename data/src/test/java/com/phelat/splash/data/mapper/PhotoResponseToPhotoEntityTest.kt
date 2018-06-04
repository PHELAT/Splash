package com.phelat.splash.data.mapper

import com.google.gson.Gson
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.data.util.TestUtils
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@RunWith(JUnit4::class)
class PhotoResponseToPhotoEntityTest {

    private lateinit var mapper: Mapper<PhotosResponse, PhotoEntity>

    @Before
    fun setUp() {
        mapper = PhotoResponseToPhotoEntity()
    }

    @Test
    fun shouldMapPhotosResponseToPhotoEntity() {

        val photosResponse = Gson().fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = mapper.mapFromTo(photosResponse)

        assertThat(photoEntity.id, IsEqual(photosResponse.id))
        assertThat(photoEntity.createdAt, IsEqual(photosResponse.createdAt))
        assertThat(photoEntity.updatedAt, IsEqual(photosResponse.updatedAt))
        assertThat(photoEntity.width, IsEqual(photosResponse.width))
        assertThat(photoEntity.height, IsEqual(photosResponse.height))
        assertThat(photoEntity.color, IsEqual(photosResponse.color))
        assertThat(photoEntity.likes, IsEqual(photosResponse.likes))
        assertThat(photoEntity.description, IsEqual(photosResponse.description))
        assertThat(photoEntity.userId, IsEqual(photosResponse.user?.id))
        assertThat(photoEntity.usersName, IsEqual(photosResponse.user?.name))
        assertThat(photoEntity.userProfileImage, IsEqual(photosResponse.user?.profileImage))
        assertThat(photoEntity.photoUrls, IsEqual(photosResponse.urls))

    }

    @Test
    fun shouldMapPhotoEntityToPhotosResponse() {

        val photosResponse = Gson().fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = mapper.mapFromTo(photosResponse)

        val photosResponseFromMapper = mapper.mapToFrom(photoEntity)

        assertThat(photoEntity.id, IsEqual(photosResponseFromMapper.id))
        assertThat(photoEntity.createdAt, IsEqual(photosResponseFromMapper.createdAt))
        assertThat(photoEntity.updatedAt, IsEqual(photosResponseFromMapper.updatedAt))
        assertThat(photoEntity.width, IsEqual(photosResponseFromMapper.width))
        assertThat(photoEntity.height, IsEqual(photosResponseFromMapper.height))
        assertThat(photoEntity.color, IsEqual(photosResponseFromMapper.color))
        assertThat(photoEntity.likes, IsEqual(photosResponseFromMapper.likes))
        assertThat(photoEntity.description, IsEqual(photosResponseFromMapper.description))
        assertThat(photoEntity.userId, IsEqual(photosResponseFromMapper.user?.id))
        assertThat(photoEntity.usersName, IsEqual(photosResponseFromMapper.user?.name))
        assertThat(photoEntity.userProfileImage, IsEqual(photosResponseFromMapper.user?.profileImage))
        assertThat(photoEntity.photoUrls, IsEqual(photosResponseFromMapper.urls))
    }

}