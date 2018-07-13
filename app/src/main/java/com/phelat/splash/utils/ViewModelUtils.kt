package com.phelat.splash.utils

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

inline fun <reified T : ViewModel> FragmentActivity.viewModelProvider(crossinline factory: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this@viewModelProvider, viewModelFactory(factory))[T::class.java]
    }
}

inline fun <reified T : ViewModel> Fragment.viewModelProvider(crossinline factory: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this@viewModelProvider, viewModelFactory(factory))[T::class.java]
    }
}

inline fun <reified T : ViewModel> viewModelFactory(crossinline factory: () -> T): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }
}