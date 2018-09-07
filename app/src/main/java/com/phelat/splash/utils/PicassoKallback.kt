package com.phelat.splash.utils

import com.squareup.picasso.Callback

class PicassoKallback<T>(private val type: T) : Callback {

    private var onSuccess: (T.() -> Unit)? = null

    private var onError: (T.() -> Unit)? = null

    override fun onSuccess() {
        onSuccess?.invoke(type)
    }

    fun onSuccess(func: T.() -> Unit) {
        onSuccess = func
    }

    override fun onError() {
        onError?.invoke(type)
    }

    fun onError(func: T.() -> Unit) {
        onError = func
    }
}