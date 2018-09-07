package com.phelat.splash.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class SplashViewHolder<DataType>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(data: DataType)

}