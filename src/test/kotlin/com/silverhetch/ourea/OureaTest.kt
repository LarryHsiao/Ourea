package com.silverhetch.ourea

import org.junit.Ignore
import org.junit.Test

class OureaTest {
    @Ignore
    @Test
    fun simple() {
        OureaImpl().run {
            init()
            addObserver { observable, data ->
                data.forEach { t, u ->

                    System.out.println(
                        """
                            Device:
                        ${u.ip()}

                """.trimIndent()
                    )
                }
            }
            Thread.sleep(100000)
            shutdown()
        }
    }
}