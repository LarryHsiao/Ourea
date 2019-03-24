package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.Target
import com.silverhetch.ourea.register.Register
import java.net.InetAddress

/**
 * Implementation of [Device].
 *
 */
class DeviceImpl(
    private val register: Register,
    private val target: Target,
    private val lastSeem: Long = System.currentTimeMillis()
) : Device {
    companion object {
        private const val VALID_DURATION = 5000
    }

    override fun ip(): InetAddress {
        return target.interfaceInetAddress()
    }

    override fun isAlive(): Boolean {
        return System.currentTimeMillis() - lastSeem < VALID_DURATION
    }

    override fun isRegistered(): Boolean {
        return register.isRegistered(target.name())
    }
}