package com.phelat.splash.data.executors

import com.phelat.splash.data.executors.base.SplashThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThread : SplashThread {

    override fun getScheduler(): Scheduler {
        return Schedulers.io()
    }

}