package com.phelat.splash.presentation.mapper

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.mapper.PhotoResponseToPhotoEntity
import com.phelat.splash.data.model.ProfileImageData
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.data.util.TestUtils
import com.phelat.splash.presentation.model.ParcelProfileImageData
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ProfileImageDataToParcelProfileImageDataTest {

    private lateinit var photoResponseToPhotoEntity: Mapper<PhotosResponse, PhotoEntity>

    private lateinit var mapper: Mapper<ProfileImageData?, ParcelProfileImageData?>

    private lateinit var gson: Gson

    @Before
    fun setUp() {
        photoResponseToPhotoEntity = PhotoResponseToPhotoEntity()
        mapper = ProfileImageDataToParcelProfileImageData()
        gson = GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Test
    fun testIfProfileImageDataMapsToParcelProfileImageData() {

        val photosResponse = gson.fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = photoResponseToPhotoEntity.mapFromTo(photosResponse)

        val parcelUserProfileImageData = mapper.mapFromTo(photoEntity.userProfileImage)

        MatcherAssert.assertThat(parcelUserProfileImageData!!.large, IsEqual(photoEntity.userProfileImage!!.large))
        MatcherAssert.assertThat(parcelUserProfileImageData.medium, IsEqual(photoEntity.userProfileImage!!.medium))
        MatcherAssert.assertThat(parcelUserProfileImageData.small, IsEqual(photoEntity.userProfileImage!!.small))

    }

    @Test
    fun testIfParcelProfileImageDataMapsToProfileImageData() {

        val photosResponse = gson.fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = photoResponseToPhotoEntity.mapFromTo(photosResponse)

        val parcelUserProfileImageData = mapper.mapFromTo(photoEntity.userProfileImage)

        val userProfileImageData = mapper.mapToFrom(parcelUserProfileImageData)

        MatcherAssert.assertThat(userProfileImageData!!.large, IsEqual(parcelUserProfileImageData!!.large))
        MatcherAssert.assertThat(userProfileImageData.medium, IsEqual(parcelUserProfileImageData.medium))
        MatcherAssert.assertThat(userProfileImageData.small, IsEqual(parcelUserProfileImageData.small))

    }

}