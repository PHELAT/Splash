package com.phelat.splash.remote.api

import com.phelat.splash.data.const.OrderBy
import com.phelat.splash.data.response.PhotosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface PhotosAPI {

    @GET("/photos")
    fun getPhotos(@Query("page") page: Int,
                  @Query("per_page") perPage: Int = 24,
                  @Query("order_by") orderBy: String = OrderBy.LATEST): Single<List<PhotosResponse>>

}