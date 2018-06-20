package com.phelat.splash.di.injectors.photolist

import com.phelat.splash.di.modules.photolist.PhotoListModule
import com.phelat.splash.di.scopes.ForActivity
import com.phelat.splash.photolist.activity.PhotoListActivity
import dagger.Subcomponent

/**
 * Created by MAHDi on 6/16/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Subcomponent(modules = [PhotoListModule::class])
@ForActivity
interface PhotoListActivityInjector {

    fun inject(activity: PhotoListActivity)

}