package com.phelat.splash.di

import com.phelat.splash.SplashApplication
import com.phelat.splash.di.injectors.photolist.PhotoListActivityInjector
import com.phelat.splash.di.modules.thread.ThreadModule
import com.phelat.splash.di.scopes.ForApplication
import com.phelat.splash.remote.di.NetworkComponent
import dagger.Component

@Component(modules = [SplashModule::class, ThreadModule::class], dependencies = [NetworkComponent::class])
@ForApplication
interface SplashComponent {

    fun inject(application: SplashApplication)

    fun photoListActivityInjector(): PhotoListActivityInjector

}