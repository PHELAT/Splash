package com.phelat.splash.photolist.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.phelat.splash.R
import com.phelat.splash.data.entity.PhotoEntity
import com.phelat.splash.photolist.viewholder.PhotoListViewHolder
import com.phelat.splash.utils.inflate
import com.squareup.picasso.Picasso

/**
 * Created by MAHDi on 6/11/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class PhotoListAdapter(private val items: List<PhotoEntity>) : RecyclerView.Adapter<PhotoListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return PhotoListViewHolder(parent.inflate(R.layout.photo_list_item))
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {

        val item = items[position]

        Picasso.with(holder.splash.context)
                .load(item.photoUrls?.thumb)
                .into(holder.splash)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}