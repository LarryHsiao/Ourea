package com.silverhetch.ourea

import java.net.InetAddress

/**
 * Implementation of [Device].
 *
 */
class DeviceImpl(
    private val inetAddress: InetAddress,
    private val lastSeem: Long = System.currentTimeMillis()
) : Device {
    companion object {
        private const val VALID_DURATION = 5000
    }

    override fun ip(): InetAddress {
        return inetAddress
    }

    override fun isAlive(): Boolean {
        return System.currentTimeMillis() - lastSeem < VALID_DURATION
    }
}