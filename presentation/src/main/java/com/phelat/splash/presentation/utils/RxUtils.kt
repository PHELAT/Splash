package com.phelat.splash.presentation.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.composite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}