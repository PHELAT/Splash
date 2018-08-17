package com.phelat.splash.presentation.provider

import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.presentation.entity.ParcelPhotoEntity
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class EmptyParcelPhotoEntityProviderTest {

    private lateinit var provider: Provider<ParcelPhotoEntity>

    @Before
    fun setUp() {
        provider = EmptyParcelPhotoEntityProvider()
    }

    @Test
    fun `test if provider returns ParcelPhotoEntity`() {

        val providedData = provider.provide()

        MatcherAssert.assertThat(providedData, notNullValue())
    }

    @Test
    fun `test if provider returns empty ParcelPhotoEntity`() {

        val providedData = provider.provide()

        MatcherAssert.assertThat(providedData.photoUrls, nullValue())
        MatcherAssert.assertThat(providedData.id, nullValue())
        MatcherAssert.assertThat(providedData.usersName, nullValue())
        MatcherAssert.assertThat(providedData.userId, nullValue())
        MatcherAssert.assertThat(providedData.description, nullValue())
        MatcherAssert.assertThat(providedData.likes, nullValue())
        MatcherAssert.assertThat(providedData.width, nullValue())
        MatcherAssert.assertThat(providedData.updatedAt, nullValue())
        MatcherAssert.assertThat(providedData.color, nullValue())
        MatcherAssert.assertThat(providedData.createdAt, nullValue())
        MatcherAssert.assertThat(providedData.height, nullValue())
        MatcherAssert.assertThat(providedData.userProfileImage, nullValue())
    }

}