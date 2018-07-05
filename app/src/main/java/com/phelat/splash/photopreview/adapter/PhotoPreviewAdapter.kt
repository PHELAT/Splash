package com.phelat.splash.photopreview.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.photopreview.fragment.PhotoPreviewFragment

/**
 * Created by MAHDi on 6/21/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoPreviewAdapter(fragmentManager: FragmentManager,
                          private val items: MutableList<PhotoEntity>) : FragmentStatePagerAdapter(fragmentManager) {

    private var pages = items.map { photoEntity ->
        PhotoPreviewFragment.instantiate(photoEntity)
    }

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.8F
    }

    override fun getItemPosition(any: Any): Int {
        // TODO : Use a better approach and prevent refreshing existing fragment
        return PagerAdapter.POSITION_NONE
    }

    fun updateItems(newItems: List<PhotoEntity>) {
        items.clear()
        items.addAll(newItems)
        pages = items.map { photoEntity ->
            PhotoPreviewFragment.instantiate(photoEntity)
        }
    }

}