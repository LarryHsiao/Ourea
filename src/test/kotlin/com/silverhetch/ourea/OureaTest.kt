package com.silverhetch.ourea

import org.junit.Test

class OureaTest {
    @Test
    fun simple() {
        OureaImpl().run {
            init()
            Thread.sleep(100000)
            shutdown()
        }
    }
}