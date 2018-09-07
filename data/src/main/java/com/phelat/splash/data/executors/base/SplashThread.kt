package com.phelat.splash.data.executors.base

import io.reactivex.Scheduler

interface SplashThread {

    fun getScheduler(): Scheduler

}