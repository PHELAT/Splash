package com.phelat.splash.presentation.photolist.contract

import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.presentation.presenter.BasePresenter
import com.phelat.splash.presentation.view.BaseView

interface PhotoListContract {

    interface View : BaseView<Presenter> {

        fun getTotalItems(): Int

        fun getLastVisibleItem(): Int

    }

    interface Presenter : BasePresenter<View> {

        fun setUp(viewModel: PhotoListViewModel)

        fun onPageScroll()

    }

}