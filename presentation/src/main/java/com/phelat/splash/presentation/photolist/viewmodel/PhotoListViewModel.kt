package com.phelat.splash.presentation.photolist.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.presentation.viewmodel.SplashViewModel

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListViewModel : SplashViewModel<MutableList<PhotoEntity>>() {

    private val mutablePhotosObservable by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MutableList<PhotoEntity>>()
    }
    val photosObservable: LiveData<MutableList<PhotoEntity>> by lazy(LazyThreadSafetyMode.NONE) {
        mutablePhotosObservable
    }

    override fun accept(mutableListOfPhotos: MutableList<PhotoEntity>) {
        if (mutablePhotosObservable.value != null) {
            val listOfPhotos = mutablePhotosObservable.value
            listOfPhotos?.addAll(mutableListOfPhotos)
            mutablePhotosObservable.value = listOfPhotos
        } else {
            mutablePhotosObservable.value = mutableListOfPhotos
        }
    }

}
