package com.phelat.splash.data.repository.photolist

import com.phelat.splash.data.datasource.DataSource
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.data.response.PhotosResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class PhotoListRepositoryTest {

    @Spy
    lateinit var remoteDataSource: DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>>

    @Spy
    lateinit var photosResponseToPhotoEntity: Mapper<PhotosResponse, PhotoEntity>

    @InjectMocks
    lateinit var repository: PhotoListRepositoryImpl

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun shouldReturnListOfPhotoEntities() {

        val photoResponse = Mockito.mock(PhotosResponse::class.java)
        val listOfPhotosResponse = arrayListOf(photoResponse)

        `when`(remoteDataSource.read(Mockito.any()))
                .thenReturn(Single.just(listOfPhotosResponse))

        val photoEntity = Mockito.mock(PhotoEntity::class.java)
        `when`(photosResponseToPhotoEntity.mapFromTo(photoResponse))
                .thenReturn(photoEntity)

        repository.getListOfPhotos()
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(remoteDataSource, times(1)).read()
        verify(photosResponseToPhotoEntity, times(1)).mapFromTo(photoResponse)
    }

}