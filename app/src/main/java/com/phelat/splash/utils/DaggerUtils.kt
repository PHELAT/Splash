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

fun AppCompatActivity.splashComponent(): SplashComponent {
    return (this.application as SplashApplication).splashComponent
}

fun AppCompatActivity.networkComponent(): NetworkComponent {
    return (this.application as SplashApplication).networkComponent
}

fun Fragment.splashComponent(): SplashComponent {
    return (this.activity?.application as SplashApplication).splashComponent
}

fun Fragment.networkComponent(): NetworkComponent {
    return (this.activity?.application as SplashApplication).networkComponent
}