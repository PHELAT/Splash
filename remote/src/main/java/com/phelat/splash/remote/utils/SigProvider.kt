package com.phelat.splash.remote.utils

/**
 * Created by MAHDi on 7/27/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

object SigProvider {

    private var lastSig: Long = -1

    fun generateSig(): Long {
        lastSig = System.currentTimeMillis()
        return lastSig
    }

    fun getLastSig(): Long {
        return lastSig
    }

}