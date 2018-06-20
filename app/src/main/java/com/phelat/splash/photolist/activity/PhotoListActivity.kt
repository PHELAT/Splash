package com.phelat.splash.photolist.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.phelat.splash.R
import com.phelat.splash.activity.SplashActivity
import com.phelat.splash.photolist.adapter.PhotoListAdapter
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.utils.splashComponent
import com.phelat.splash.utils.viewModelProvider
import kotlinx.android.synthetic.main.photo_list_activity.*
import javax.inject.Inject

/**
 * Created by MAHDi on 6/6/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListActivity : SplashActivity<PhotoListContract.Presenter>(), PhotoListContract.View {

    @Inject
    lateinit var presenter: PhotoListContract.Presenter

    val photoListViewModel by viewModelProvider {
        PhotoListViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashComponent.photoListActivityInjector()
                .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_list_activity)

        photoListRecycler.layoutManager = LinearLayoutManager(this)

        presenter.subscribe(this)

        photoListViewModel.photosObservable.observe(this, Observer { photoEntities ->
            photoEntities?.let { nonNullPhotoEntities ->
                photoListRecycler.adapter = PhotoListAdapter(nonNullPhotoEntities)
            }
        })

        presenter.setUp(photoListViewModel)

    }

    override fun onDestroy() {

        presenter.unsubscribe()

        photoListViewModel.photosObservable.removeObservers(this)

        super.onDestroy()
    }

}