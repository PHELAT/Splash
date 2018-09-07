package com.phelat.splash.presentation.view

import android.support.annotation.StringRes

interface BaseView<in Presenter> {

    fun showToast(@StringRes message: Int)

}