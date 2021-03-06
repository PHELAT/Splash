package com.phelat.splash.activity

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.phelat.splash.presentation.presenter.BasePresenter
import com.phelat.splash.presentation.view.BaseView

abstract class SplashActivity<Presenter : BasePresenter<*>> : AppCompatActivity(), BaseView<Presenter> {

    override fun showToast(message: Int) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

}