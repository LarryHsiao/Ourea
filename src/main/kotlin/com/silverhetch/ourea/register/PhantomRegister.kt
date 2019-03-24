package com.silverhetch.ourea.register

/**
 * Phantom of [Register]
 */
class PhantomRegister : Register {
    override fun authCode(id: String): String {
        return ""
    }

    override fun register(id: String, auth: String) {
    }

    override fun isRegistered(id: String): Boolean {
        return false
    }
}