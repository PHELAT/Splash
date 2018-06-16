package com.phelat.splash.di

import com.phelat.splash.di.injectors.photolist.PhotoListActivityInjector
import com.phelat.splash.di.scopes.ForApplication
import com.phelat.splash.remote.di.NetworkComponent
import dagger.Component

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Component(modules = [SplashModule::class], dependencies = [NetworkComponent::class])
@ForApplication
interface SplashComponent {

    fun photoListActivityInjector(): PhotoListActivityInjector

}