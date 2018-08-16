package com.phelat.splash.presentation.mapper

import com.google.gson.Gson
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.mapper.PhotoResponseToPhotoEntity
import com.phelat.splash.data.model.PhotoUrlsData
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.data.util.TestUtils
import com.phelat.splash.presentation.model.ParcelPhotoUrlsData
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by MAHDi on 7/27/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

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

        MatcherAssert.assertThat(parcelPhotoUrls!!.full, IsEqual(photoEntity.photoUrls!!.full))
        MatcherAssert.assertThat(parcelPhotoUrls.raw, IsEqual(photoEntity.photoUrls!!.raw))
        MatcherAssert.assertThat(parcelPhotoUrls.regular, IsEqual(photoEntity.photoUrls!!.regular))
        MatcherAssert.assertThat(parcelPhotoUrls.small, IsEqual(photoEntity.photoUrls!!.small))
        MatcherAssert.assertThat(parcelPhotoUrls.thumb, IsEqual(photoEntity.photoUrls!!.thumb))

    }

}