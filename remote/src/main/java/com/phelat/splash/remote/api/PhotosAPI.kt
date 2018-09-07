package com.phelat.splash.remote.api

import com.phelat.splash.data.const.OrderBy
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.remote.const.RemoteConstant
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosAPI {

    @GET("/photos")
    fun getPhotos(
            @Query("page") page: Int,
            @Query("per_page") perPage: Int = RemoteConstant.PER_PAGE,
            @Query("order_by") orderBy: String = OrderBy.LATEST,
            @Query("sig") sig: Long
    ): Single<List<PhotosResponse>>

}