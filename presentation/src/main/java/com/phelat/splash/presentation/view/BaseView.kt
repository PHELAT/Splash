package com.phelat.splash.presentation.view

import android.support.annotation.StringRes
import com.phelat.splash.presentation.presenter.BasePresenter

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface BaseView<Presenter : BasePresenter> {

    fun showToast(@StringRes message: Int)

}