package com.phelat.splash.data.mapper

interface Mapper<From, To> {

    fun mapFromTo(from: From): To

    fun mapToFrom(to: To): From

}