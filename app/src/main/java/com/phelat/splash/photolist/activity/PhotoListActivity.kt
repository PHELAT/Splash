package com.phelat.splash.photolist.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.phelat.splash.R
import com.phelat.splash.activity.SplashActivity
import com.phelat.splash.photopreview.adapter.PhotoPreviewAdapter
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

class PhotoListActivity : SplashActivity<PhotoListContract.Presenter>(),
        PhotoListContract.View,
        ViewPager.OnPageChangeListener {

    @Inject
    lateinit var presenter: PhotoListContract.Presenter

    private val photoListViewModel by viewModelProvider {
        PhotoListViewModel()
    }

    private val photoListPagerAdapter by lazy(LazyThreadSafetyMode.NONE) {
        PhotoPreviewAdapter(supportFragmentManager, ArrayList())
    }

    private val offScreenPageLimit = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        splashComponent.photoListActivityInjector()
                .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.photo_list_activity)

        previewPager.offscreenPageLimit = offScreenPageLimit
        previewPager.adapter = photoListPagerAdapter
        previewPager.addOnPageChangeListener(this)

        presenter.subscribe(this)

        photoListViewModel.photosObservable.observe(this, Observer { photoEntities ->
            if (photoListPagerAdapter.shimmerMode) {
                photoListPagerAdapter.shimmerMode = false
            }
            photoEntities?.let { nonNullPhotoEntities ->
                photoListPagerAdapter.items.clear()
                photoListPagerAdapter.items.addAll(nonNullPhotoEntities)
                photoListPagerAdapter.notifyDataSetChanged()
            }
        })

        presenter.setUp(photoListViewModel)

    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        presenter.onPageSelected(position)
    }

    override fun onDestroy() {

        presenter.unsubscribe()

        photoListViewModel.photosObservable.removeObservers(this)

        super.onDestroy()
    }

}