package com.phelat.splash.remote.api

import com.phelat.splash.remote.di.NetworkModule
import com.phelat.splash.remote.utils.TestUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PhotosAPITest {

    private lateinit var mockWebServer: MockWebServer

    private lateinit var api: PhotosAPI

    @Before
    fun setUp() {

        mockWebServer = MockWebServer()
        mockWebServer.start()

        val networkComponent = DaggerNetworkComponent.builder()
                .networkModule(NetworkModule("http://" + mockWebServer.hostName + ":" + mockWebServer.port))
                .build()

        api = networkComponent.photosAPI()
    }

    @Test
    fun `test if it maps the server result to appropriate data classes`() {

        val response = MockResponse()
        response.setResponseCode(200)
        response.setBody(TestUtils.readResource("json/photos/photos_response.json", this.javaClass.classLoader))
        mockWebServer.enqueue(response)

        api.getPhotos(0, 10, sig = 123)
                .test()
                .assertNoErrors()
                .assertValue {
                    it.size == 10 && it[0].user != null && it[0].urls != null && it[0].user?.profileImage != null
                }
                .assertComplete()

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}