package com.phelat.splash.presentation.photolist.contract

import com.phelat.splash.presentation.photolist.viewmodel.PhotoListViewModel
import com.phelat.splash.presentation.presenter.BasePresenter
import com.phelat.splash.presentation.view.BaseView

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface PhotoListContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter<View> {

        fun setUp(viewModel: PhotoListViewModel)

        fun onPageSelected(position: Int)

    }

}