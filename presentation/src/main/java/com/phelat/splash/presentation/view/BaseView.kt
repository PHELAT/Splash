package com.phelat.splash.presentation.view

import android.support.annotation.StringRes

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface BaseView<in Presenter> {

    fun showToast(@StringRes message: Int)

}