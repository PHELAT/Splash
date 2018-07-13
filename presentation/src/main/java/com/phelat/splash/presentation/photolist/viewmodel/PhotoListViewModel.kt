package com.phelat.splash.presentation.photolist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.mapper.Mapper
import com.phelat.splash.presentation.entity.ParcelPhotoEntity
import com.phelat.splash.presentation.viewmodel.SplashViewModel

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListViewModel(

        private val photoEntityMapper: Mapper<PhotoEntity, ParcelPhotoEntity>

) : SplashViewModel<MutableList<PhotoEntity>>() {

    private val mutablePhotosObservable by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MutableList<ParcelPhotoEntity>>()
    }
    val photosObservable: LiveData<MutableList<ParcelPhotoEntity>> by lazy(LazyThreadSafetyMode.NONE) {
        mutablePhotosObservable
    }

    override fun accept(mutableListOfPhotos: MutableList<PhotoEntity>) {
        if (mutablePhotosObservable.value != null) {
            val listOfPhotos = mutablePhotosObservable.value
            listOfPhotos?.addAll(mapPhotoEntity(mutableListOfPhotos))
            mutablePhotosObservable.value = listOfPhotos
        } else {
            mutablePhotosObservable.value = mapPhotoEntity(mutableListOfPhotos)
        }
    }

    private fun mapPhotoEntity(mutableListOfPhotos: MutableList<PhotoEntity>): MutableList<ParcelPhotoEntity> {
        val list = mutableListOf<ParcelPhotoEntity>()
        return mutableListOfPhotos.mapTo(list) { photoEntity ->
            photoEntityMapper.mapFromTo(photoEntity)
        }
    }

}
