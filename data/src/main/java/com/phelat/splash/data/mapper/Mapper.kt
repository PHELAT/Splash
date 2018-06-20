package com.phelat.splash.data.mapper

/**
 * Created by MAHDi on 6/4/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

interface Mapper<From, To> {

    fun mapFromTo(from: From): To

    fun mapToFrom(to: To): From

}