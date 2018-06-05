package com.phelat.splash.presentation.photolist.presenter

import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.data.repository.photolist.PhotoListRepository
import com.phelat.splash.presentation.BuildConfig
import com.phelat.splash.presentation.executors.qualifiers.BackgroundThreadQ
import com.phelat.splash.presentation.executors.qualifiers.MainThreadQ
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.presentation.utils.composite
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import javax.inject.Inject

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

    override fun subscribe(view: PhotoListContract.View) {
        this.view = view
    }

    override fun setUp(viewModel: PhotoListViewModel) {
        this.viewModel = viewModel
        if (viewModel.photosObservable.value == null) {
            fetchPhotos()
        }
    }

    private fun fetchPhotos() {
        repository.getListOfPhotos()
                .subscribeOn(backgroundThread.getScheduler())
                .observeOn(mainThread.getScheduler())
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