package com.phelat.splash.data.datasource

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface DataSource {

    interface SingleCreatable<InputType, OutputType> {

        fun create(input: InputType? = null): Single<OutputType>

    }

    interface SingleReadable<InputType, OutputType> {

        fun read(input: InputType? = null): Single<OutputType>

    }

    interface CompleteCreatable<InputType> {

        fun read(input: InputType? = null): Completable

    }

}