package com.phelat.splash.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by MAHDi on 6/21/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

abstract class SplashViewHolder<DataType>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: DataType)

}