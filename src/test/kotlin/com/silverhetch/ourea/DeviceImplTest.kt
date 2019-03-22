package com.silverhetch.ourea

import org.junit.Assert.*
import org.junit.Test
import java.net.InetAddress

/**
 * Test for [DeviceImpl]
 */
class DeviceImplTest {
    @Test
    fun checkInetAddress() {
        assertEquals(
            InetAddress.getByName("0.0.0.0"),
            DeviceImpl(
                InetAddress.getByName("0.0.0.0"),
                System.currentTimeMillis()
            ).ip()
        )
    }

    @Test
    fun alive() {
        assertTrue(
            DeviceImpl(
                InetAddress.getByName("0.0.0.0"),
                System.currentTimeMillis()
            ).isAlive()
        )
    }

    @Test
    fun expired() {
        assertFalse(
            DeviceImpl(
                InetAddress.getByName("0.0.0.0"),
                System.currentTimeMillis() - 5001
            ).isAlive()
        )
    }
}