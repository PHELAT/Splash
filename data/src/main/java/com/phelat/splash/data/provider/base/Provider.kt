package com.phelat.splash.data.provider.base

/**
 * Created by MAHDi on 6/27/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface Provider<Output> {

    fun provide(): Output

    interface WithInput<Input, Output> {

        fun provide(input: Input): Output

    }

}