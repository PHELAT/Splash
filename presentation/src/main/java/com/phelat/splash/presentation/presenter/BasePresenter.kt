package com.phelat.splash.presentation.presenter

import com.phelat.splash.presentation.view.BaseView

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface BasePresenter {

    fun subscribe(view: BaseView<*>)

    fun unsubscribe()

}