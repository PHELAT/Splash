package com.phelat.splash.di.modules.thread

import com.phelat.splash.data.executors.BackgroundThread
import com.phelat.splash.data.executors.base.SplashThread
import com.phelat.splash.di.scopes.ForApplication
import com.phelat.splash.presentation.executors.MainThread
import com.phelat.splash.presentation.executors.qualifiers.BackgroundThreadQ
import com.phelat.splash.presentation.executors.qualifiers.MainThreadQ
import dagger.Module
import dagger.Provides

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class ThreadModule {

    @Provides
    @ForApplication
    @MainThreadQ
    fun provideMainThread(): SplashThread {
        return MainThread()
    }

    @Provides
    @ForApplication
    @BackgroundThreadQ
    fun provideBackgroundThread(): SplashThread {
        return BackgroundThread()
    }

}