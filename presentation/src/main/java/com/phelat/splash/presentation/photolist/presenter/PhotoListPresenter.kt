package com.phelat.splash.presentation.photolist.presenter

import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.data.repository.photolist.PhotoListRepository
import com.phelat.splash.data.request.GetPhotoRequest
import com.phelat.splash.presentation.BuildConfig
import com.phelat.splash.presentation.executors.qualifiers.BackgroundThreadQ
import com.phelat.splash.presentation.executors.qualifiers.MainThreadQ
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.presentation.utils.composite
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject
import kotlin.math.roundToInt

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListPresenter @Inject constructor(

        private val repository: PhotoListRepository,
        private val compositeDisposable: CompositeDisposable,
        @MainThreadQ private val mainThread: SplashThread,
        @BackgroundThreadQ private val backgroundThread: SplashThread

) : PhotoListContract.Presenter {

    lateinit var view: PhotoListContract.View

    lateinit var viewModel: PhotoListViewModel

    private val newListRequestGap = 0.8

    // TODO : USE STATE
    private var isLoading = false

    private var getPhotoRequest = GetPhotoRequest(1)

    override fun subscribe(view: PhotoListContract.View) {
        this.view = view
    }

    override fun setUp(viewModel: PhotoListViewModel) {
        this.viewModel = viewModel
        if (viewModel.photosObservable.value == null) {
            fetchPhotos()
        }
    }

    override fun onPageSelected(position: Int) {
        viewModel.photosObservable.value?.let { nonNullPhotoEntities ->
            if ((nonNullPhotoEntities.size * newListRequestGap).roundToInt() <= position && !isLoading) {
                fetchPhotos()
            }
        }
    }

    private fun fetchPhotos() {

        isLoading = true

        repository.getListOfPhotos(getPhotoRequest)
                .subscribeOn(backgroundThread.getScheduler())
                .observeOn(mainThread.getScheduler())
                .doOnSuccess { _ ->
                    isLoading = false
                    getPhotoRequest = getPhotoRequest.copy(getPhotoRequest.page + 1)
                }
                .subscribe(viewModel, Consumer { throwable ->
                    if (BuildConfig.DEBUG) {
                        throwable.printStackTrace()
                    }
                })
                .composite(compositeDisposable)
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

}