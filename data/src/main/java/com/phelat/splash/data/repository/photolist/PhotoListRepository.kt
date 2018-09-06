package com.phelat.splash.data.repository.photolist

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.request.GetPhotoRequest
import io.reactivex.Single

interface PhotoListRepository {

    fun getListOfPhotos(request: GetPhotoRequest? = null): Single<MutableList<PhotoEntity>>

}