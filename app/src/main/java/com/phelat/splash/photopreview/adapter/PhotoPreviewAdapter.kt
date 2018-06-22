package com.phelat.splash.photopreview.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.ViewGroup
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.photopreview.fragment.PhotoPreviewFragment
import com.phelat.splash.photopreview.fragment.PhotoPreviewShimmerFragment

/**
 * Created by MAHDi on 6/21/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoPreviewAdapter(fragmentManager: FragmentManager,
                          val items: MutableList<PhotoEntity>,
                          var shimmerMode: Boolean = true) : FragmentPagerAdapter(fragmentManager) {

    private lateinit var primaryItem: Fragment

    override fun getItem(position: Int): Fragment {
        return if (items.isEmpty() && shimmerMode) {
            PhotoPreviewShimmerFragment()
        } else {
            PhotoPreviewFragment.instantiate(items[position])
        }
    }

    override fun getCount(): Int {
        return if (items.isEmpty() && shimmerMode) 2 else items.size
    }

    override fun getPageWidth(position: Int): Float {
        return 0.8F
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, any: Any) {
        super.setPrimaryItem(container, position, any)
        primaryItem = any as Fragment
    }

    override fun getItemPosition(any: Any): Int {
        return if (any === primaryItem) {
            PagerAdapter.POSITION_UNCHANGED
        } else {
            PagerAdapter.POSITION_NONE
        }
    }

}