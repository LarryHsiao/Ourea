package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.PhantomTarget
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
            InetAddress.getByName("127.0.0.1"),
            DeviceImpl(
                PhantomTarget(),
                System.currentTimeMillis()
            ).ip()
        )
    }

    @Test
    fun alive() {
        assertTrue(
            DeviceImpl(
                PhantomTarget(),
                System.currentTimeMillis()
            ).isAlive()
        )
    }

    @Test
    fun expired() {
        assertFalse(
            DeviceImpl(
                PhantomTarget(),
                System.currentTimeMillis() - 5001
            ).isAlive()
        )
    }
}