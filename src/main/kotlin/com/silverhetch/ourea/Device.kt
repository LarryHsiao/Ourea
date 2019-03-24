package com.silverhetch.ourea

import java.net.InetAddress

/**
 * Represent a device in ourea scanning scope.
 */
interface Device {
    /**
     * The ip this device have
     */
    fun ip(): InetAddress

    /**
     * Determine if this device is still alive that we have keep receiving response from it.
     */
    fun isAlive(): Boolean

    /**
     * Determine if this device is registered device list.
     */
    fun isRegistered(): Boolean
}