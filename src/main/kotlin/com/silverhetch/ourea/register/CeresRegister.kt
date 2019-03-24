package com.silverhetch.ourea.register

import com.silverhetch.clotho.storage.Ceres

class CeresRegister(private val ceres: Ceres) : Register {
    companion object {
        private const val KEY_AUTH_CODE_ = "KEY_AUTH_CODE_"
    }

    override fun isRegistered(id: String): Boolean {
        return ceres.get("$KEY_AUTH_CODE_$id").isNotEmpty()
    }

    override fun register(id: String, auth: String) {
        ceres.store("$KEY_AUTH_CODE_$id", auth)
    }

    override fun authCode(id: String): String {
        return ceres.get("$KEY_AUTH_CODE_$id")
    }
}