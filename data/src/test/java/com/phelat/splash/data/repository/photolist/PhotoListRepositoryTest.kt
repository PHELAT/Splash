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
import org.mockito.*
import org.mockito.Mockito.*
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

    @Mock
    lateinit var photoResponse: PhotosResponse

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        val listOfPhotosResponse = arrayListOf(photoResponse)

        `when`(remoteDataSource.read(Mockito.any()))
                .thenReturn(Single.just(listOfPhotosResponse))

        val photoEntity = Mockito.mock(PhotoEntity::class.java)
        `when`(photosResponseToPhotoEntity.mapFromTo(photoResponse))
                .thenReturn(photoEntity)
    }

    @Test
    fun shouldFetchPhotosFromRemoteDataSource() {

        repository.getListOfPhotos()
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(remoteDataSource, times(1)).read()
    }

    @Test
    fun shouldMapPhotosResponseToPhotoEntity() {

        repository.getListOfPhotos()
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(photosResponseToPhotoEntity, times(1)).mapFromTo(photoResponse)
    }

}