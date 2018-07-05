package com.phelat.splash.remote.datasource

import com.phelat.splash.data.const.OrderBy
import com.phelat.splash.data.datasource.DataSource
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.remote.api.PhotosAPI
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class GetPhotosRemoteDataSourceTest {

    @Mock
    lateinit var photosAPI: PhotosAPI

    lateinit var dataSource: DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        dataSource = GetPhotosRemoteDataSource(photosAPI)
    }

    @Test
    fun shouldReturnListOfPhotosWithSpecificInput() {

        val mockPhotosResponse = Mockito.mock(PhotosResponse::class.java)
        val listOfPhotos = arrayListOf(mockPhotosResponse)

        doReturn(Single.just(listOfPhotos))
                .`when`(photosAPI)
                .getPhotos(2, 24, OrderBy.OLDEST)

        val input = GetPhotoRequest(2, 24, OrderBy.OLDEST)

        dataSource.read(input)
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(photosAPI, times(1)).getPhotos(input.page, input.perPage, input.orderBy)
    }

    @Test
    fun shouldReturnListOfPhotosWithoutSpecificInput() {

        val mockPhotosResponse = Mockito.mock(PhotosResponse::class.java)
        val listOfPhotos = arrayListOf(mockPhotosResponse)

        doReturn(Single.just(listOfPhotos))
                .`when`(photosAPI)
                .getPhotos(1, 24, OrderBy.LATEST)

        dataSource.read()
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(photosAPI, times(1)).getPhotos(1, 24, OrderBy.LATEST)
    }

}