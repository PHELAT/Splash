package com.phelat.splash.presentation.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

fun Disposable.composite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}