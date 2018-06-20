package com.phelat.splash.presentation.executors

import com.phelat.splash.data.executors.base.SplashThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

class MainThread : SplashThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

}