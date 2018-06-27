package com.phelat.splash.data.provider.base

/**
 * Created by MAHDi on 6/27/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface Provider<Provides> {

    fun provide(): Provides

    interface WithInput<Input, Provides> {

        fun provide(input: Input)

    }

}