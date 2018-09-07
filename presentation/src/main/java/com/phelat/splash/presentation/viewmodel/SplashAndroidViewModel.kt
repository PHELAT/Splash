package com.phelat.splash.presentation.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import io.reactivex.functions.Consumer

abstract class SplashAndroidViewModel<InputType>(

        application: Application

) : AndroidViewModel(application), Consumer<InputType>