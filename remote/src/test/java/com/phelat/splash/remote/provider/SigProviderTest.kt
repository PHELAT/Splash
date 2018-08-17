package com.phelat.splash.remote.provider

import com.phelat.splash.data.provider.base.Provider
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SigProviderTest {

    private lateinit var provider: Provider<Long>

    @Before
    fun setUp() {
        provider = SigProvider()
    }

    @Test
    fun `test if it returns the correct time stamp`() {

        val timeStamp = provider.provide()
        Thread.sleep(1)
        val timeStamp2 = provider.provide()

        assertTrue(timeStamp < timeStamp2)
    }

}