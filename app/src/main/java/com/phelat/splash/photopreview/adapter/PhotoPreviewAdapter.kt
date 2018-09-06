package com.phelat.splash.photopreview.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.phelat.splash.R
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.photopreview.holder.PhotoPreviewHolder
import com.phelat.splash.photopreview.utils.PhotoListDiffUtil
import com.phelat.splash.utils.inflate

class PhotoPreviewAdapter(

        private val items: MutableList<PhotoEntity>

) : RecyclerView.Adapter<PhotoPreviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoPreviewHolder {
        return PhotoPreviewHolder(parent.inflate(R.layout.photo_list_item))
    }

    override fun onBindViewHolder(holder: PhotoPreviewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: MutableList<PhotoEntity>) {

        val diffUtil = PhotoListDiffUtil(newItems, items)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)

        items.clear()
        items.addAll(newItems)

        diffUtilResult.dispatchUpdatesTo(this)
    }

}