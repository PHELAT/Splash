package com.phelat.splash.data.executors.base

import io.reactivex.Scheduler

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface SplashThread {

    fun getScheduler(): Scheduler

}