package com.silverhetch.ourea.register

/**
 * Register model
 */
interface Register {
    /**
     * The auth code for the communication
     */
    fun authCode(id: String): String

    /**
     * Register for auth code
     */
    fun register(id: String, auth: String)

    /**
     * Determine if the device is registered before
     */
    fun isRegistered(id: String): Boolean
}