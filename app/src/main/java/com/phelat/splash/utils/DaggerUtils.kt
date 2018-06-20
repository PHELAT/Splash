package com.phelat.splash.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.phelat.splash.SplashApplication
import com.phelat.splash.di.SplashComponent
import com.phelat.splash.remote.di.NetworkComponent

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

val AppCompatActivity.splashComponent: SplashComponent
    get() {
    return (this.application as SplashApplication).splashComponent
}

val AppCompatActivity.networkComponent: NetworkComponent
    get() {
    return (this.application as SplashApplication).networkComponent
}

val Fragment.splashComponent: SplashComponent
    get() {
    return (this.activity?.application as SplashApplication).splashComponent
}

val Fragment.networkComponent: NetworkComponent
    get() {
    return (this.activity?.application as SplashApplication).networkComponent
}