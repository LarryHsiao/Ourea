package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.Target
import java.net.InetAddress

/**
 * Implementation of [Device].
 *
 */
class DeviceImpl(
    private val inetAddress: Target,
    private val lastSeem: Long = System.currentTimeMillis()
) : Device {
    companion object {
        private const val VALID_DURATION = 5000
    }

    override fun ip(): InetAddress {
        return inetAddress.interfaceInetAddress()
    }

    override fun isAlive(): Boolean {
        return System.currentTimeMillis() - lastSeem < VALID_DURATION
    }

    override fun isRegistered(): Boolean {
        // @todo #1 implement with register model
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}