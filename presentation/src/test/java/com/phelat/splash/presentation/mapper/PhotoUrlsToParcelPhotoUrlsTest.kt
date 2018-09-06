package com.phelat.splash.presentation.mapper

import com.google.gson.Gson
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.mapper.PhotoResponseToPhotoEntity
import com.phelat.splash.data.model.PhotoUrlsData
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.data.util.TestUtils
import com.phelat.splash.presentation.model.ParcelPhotoUrlsData
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.hamcrest.core.IsNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotoUrlsToParcelPhotoUrlsTest {

    private lateinit var photoResponseToPhotoEntity: Mapper<PhotosResponse, PhotoEntity>

    private lateinit var mapper: Mapper<PhotoUrlsData?, ParcelPhotoUrlsData?>

    @Before
    fun setUp() {
        photoResponseToPhotoEntity = PhotoResponseToPhotoEntity()
        mapper = PhotoUrlsDataToParcelPhotoUrlsData()
    }

    @Test
    fun `test if PhotoUrlsData maps to ParcelPhotoUrls`() {

        val photosResponse = Gson().fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = photoResponseToPhotoEntity.mapFromTo(photosResponse)

        val parcelPhotoUrls = mapper.mapFromTo(photoEntity.photoUrls)

        assertThat(parcelPhotoUrls!!.full, IsEqual(photoEntity.photoUrls!!.full))
        assertThat(parcelPhotoUrls.raw, IsEqual(photoEntity.photoUrls!!.raw))
        assertThat(parcelPhotoUrls.regular, IsEqual(photoEntity.photoUrls!!.regular))
        assertThat(parcelPhotoUrls.small, IsEqual(photoEntity.photoUrls!!.small))
        assertThat(parcelPhotoUrls.thumb, IsEqual(photoEntity.photoUrls!!.thumb))

    }

    @Test
    fun `test if ParcelPhotoUrls maps to PhotoUrlsData`() {

        val photosResponse = Gson().fromJson(TestUtils.readResource("json/photos/photos_response.json",
                this.javaClass.classLoader),
                PhotosResponse::class.java)

        val photoEntity = photoResponseToPhotoEntity.mapFromTo(photosResponse)

        val parcelPhotoUrls = mapper.mapFromTo(photoEntity.photoUrls)

        val photoUrls = mapper.mapToFrom(parcelPhotoUrls)

        assertThat(photoUrls!!.full, IsEqual(photoEntity.photoUrls!!.full))
        assertThat(photoUrls.raw, IsEqual(photoEntity.photoUrls!!.raw))
        assertThat(photoUrls.regular, IsEqual(photoEntity.photoUrls!!.regular))
        assertThat(photoUrls.small, IsEqual(photoEntity.photoUrls!!.small))
        assertThat(photoUrls.thumb, IsEqual(photoEntity.photoUrls!!.thumb))

    }

    @Test
    fun `test if mapFromTo returns null when input value is null`() {

        val parcelPhotoUrls = mapper.mapFromTo(null)

        assertThat(parcelPhotoUrls, IsNull())

    }

    @Test
    fun `test if mapToFrom returns null when input value is null`() {

        val parcelPhotoUrls = mapper.mapToFrom(null)

        assertThat(parcelPhotoUrls, IsNull())

    }

}