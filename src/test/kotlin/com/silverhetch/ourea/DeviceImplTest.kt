package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.PhantomTarget
import com.silverhetch.ourea.register.PhantomRegister
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
                PhantomRegister(),
                InetAddress.getByName("127.0.01"),
                System.currentTimeMillis()
            ).ip()
        )
    }

    @Test
    fun alive() {
        assertTrue(
            DeviceImpl(
                PhantomRegister(),
                InetAddress.getByName("127.0.01"),
                System.currentTimeMillis()
            ).isAlive()
        )
    }

    @Test
    fun expired() {
        assertFalse(
            DeviceImpl(
                PhantomRegister(),
                InetAddress.getByName("127.0.01"),
                System.currentTimeMillis() - 5001
            ).isAlive()
        )
    }
}