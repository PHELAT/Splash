package com.phelat.splash.photolist.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.phelat.splash.R
import com.phelat.splash.activity.SplashActivity
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.data.provider.base.Provider
import com.phelat.splash.photopreview.adapter.PhotoPreviewAdapter
import com.phelat.splash.presentation.photolist.contract.PhotoListContract
import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.utils.splashComponent
import kotlinx.android.synthetic.main.photo_list_activity.*
import javax.inject.Inject

/**
 * Created by MAHDi on 6/6/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListActivity : SplashActivity<PhotoListContract.Presenter>(), PhotoListContract.View {

    @Inject
    lateinit var presenter: PhotoListContract.Presenter

    @Inject
    lateinit var emptyPhotoEntityProvider: Provider<PhotoEntity>

    private lateinit var photoListViewModel: PhotoListViewModel

    private val photoListAdapter by lazy(LazyThreadSafetyMode.NONE) {
        val emptyPhotoEntity = emptyPhotoEntityProvider.provide()
        PhotoPreviewAdapter(mutableListOf(emptyPhotoEntity, emptyPhotoEntity))
    }

    private val layoutManager by lazy(LazyThreadSafetyMode.NONE) {
        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        splashComponent.photoListActivityInjector()
                .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_list_activity)

        photoListViewModel = ViewModelProviders.of(this).get(PhotoListViewModel::class.java)

        previewRecycler.layoutManager = layoutManager
        previewRecycler.adapter = photoListAdapter
        previewRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                presenter.onPageScroll()
            }
        })

        presenter.subscribe(this)

        photoListViewModel.photosObservable.observe(this, Observer { photoEntities ->
            photoEntities?.let { nonNullPhotoEntities ->
                photoListAdapter.updateItems(nonNullPhotoEntities)
            }
        })

        presenter.setUp(photoListViewModel)

    }

    override fun getTotalItems(): Int {
        return layoutManager.itemCount
    }

    override fun getLastVisibleItem(): Int {
        return layoutManager.findLastVisibleItemPosition()
    }

    override fun onDestroy() {

        presenter.unsubscribe()

        photoListViewModel.photosObservable.removeObservers(this)

        super.onDestroy()
    }

}