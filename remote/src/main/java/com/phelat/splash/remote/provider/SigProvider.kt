package com.phelat.splash.remote.provider

import com.phelat.splash.data.provider.base.Provider

class SigProvider : Provider<Long> {

    override fun provide(): Long {
        return System.currentTimeMillis()
    }

}