package com.phelat.splash.data.provider.base

interface Provider<Output> {

    fun provide(): Output

    interface WithInput<Input, Output> {

        fun provide(input: Input): Output

    }

}