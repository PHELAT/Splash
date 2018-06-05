package com.phelat.splash.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer

/**
 * Created by MAHDi on 6/5/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

abstract class SplashViewModel<InputType> : ViewModel(), Consumer<InputType>