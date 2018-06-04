package com.phelat.splash.remote.datasource

import com.phelat.splash.data.datasource.DataSource
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.data.response.PhotosResponse
import com.phelat.splash.remote.api.PhotosAPI
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class GetPhotosRemoteDataSource @Inject constructor(private val api: PhotosAPI)
    : DataSource.SingleReadable<GetPhotoRequest, List<PhotosResponse>> {

    override fun read(input: GetPhotoRequest?): Single<List<PhotosResponse>> {
        return input?.let { nonNullInput ->
            api.getPhotos(nonNullInput.page, nonNullInput.perPage, nonNullInput.orderBy)
        } ?: api.getPhotos(0)
    }

}