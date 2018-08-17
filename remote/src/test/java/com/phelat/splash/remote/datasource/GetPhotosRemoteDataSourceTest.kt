package com.phelat.splash.remote.datasource

import com.phelat.splash.data.const.OrderBy
import com.phelat.splash.data.datasource.DataSource
import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.remote.api.PhotosAPI
import com.phelat.splash.remote.const.RemoteConstant
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

    @Mock
    lateinit var sigProvider: Provider<Long>

    lateinit var dataSource: DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>>

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)

        doReturn(123)
                .`when`(sigProvider)
                .provide()

        dataSource = GetPhotosRemoteDataSource(photosAPI, sigProvider)
    }

    @Test
    fun `should return list of photos with a specific getPhotoRequest`() {

        val mockPhotosResponse = Mockito.mock(PhotosResponse::class.java)
        val listOfPhotos = arrayListOf(mockPhotosResponse)

        doReturn(Single.just(listOfPhotos))
                .`when`(photosAPI)
                .getPhotos(2, 24, OrderBy.OLDEST, 123)

        val input = GetPhotoRequest(2, 24, OrderBy.OLDEST)

        dataSource.read(input)
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(photosAPI, times(1)).getPhotos(input.page, input.perPage, input.orderBy, 123)
    }

    @Test
    fun `should return list of photos without specific input`() {

        val mockPhotosResponse = Mockito.mock(PhotosResponse::class.java)
        val listOfPhotos = arrayListOf(mockPhotosResponse)

        doReturn(Single.just(listOfPhotos))
                .`when`(photosAPI)
                .getPhotos(1, RemoteConstant.PER_PAGE, OrderBy.LATEST, 123)

        dataSource.read()
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(photosAPI, times(1)).getPhotos(1, sig = 123)
    }

}