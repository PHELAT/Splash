package com.phelat.splash.data.repository.photolist

import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.request.GetPhotoRequest
import io.reactivex.Single

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface PhotoListRepository {

    fun getListOfPhotos(request: GetPhotoRequest? = null): Single<MutableList<PhotoEntity>>

}