package com.phelat.splash.presentation.presenter

import com.phelat.splash.presentation.view.BaseView

interface BasePresenter<in View : BaseView<*>> {

    fun subscribe(view: View)

    fun unsubscribe()

}