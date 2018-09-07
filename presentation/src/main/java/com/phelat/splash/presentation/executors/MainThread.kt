package com.phelat.splash.presentation.executors

import com.phelat.splash.data.executors.base.SplashThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class MainThread : SplashThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}