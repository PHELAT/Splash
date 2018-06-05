package com.phelat.splash.presentation.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.functions.Consumer

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

abstract class SplashAndroidViewModel<InputType>(application: Application)
    : AndroidViewModel(application), Consumer<InputType>