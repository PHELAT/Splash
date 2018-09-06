package com.phelat.splash.di.injectors.photolist

import com.phelat.splash.di.modules.photolist.PhotoListModule
import com.phelat.splash.di.scopes.ForActivity
import com.phelat.splash.photolist.activity.PhotoListActivity
import dagger.Subcomponent

@Subcomponent(modules = [PhotoListModule::class])
@ForActivity
interface PhotoListActivityInjector {

    fun inject(activity: PhotoListActivity)

}