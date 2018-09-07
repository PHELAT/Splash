package com.phelat.splash.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.functions.Consumer

abstract class SplashViewModel<InputType> : ViewModel(), Consumer<InputType>