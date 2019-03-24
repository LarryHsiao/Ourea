package com.silverhetch.ourea.register

import com.silverhetch.clotho.database.sqlite.SQLiteConn
import com.silverhetch.clotho.storage.DbCeres
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class CeresRegisterTest {
    @Test
    fun registered() {
        CeresRegister(
            DbCeres(
                SQLiteConn(
                    File.createTempFile("prefix", "suffix").absolutePath
                )
            )
        ).run {
            register("ID", "AUTH")
            assertTrue(isRegistered("ID"))
        }
    }


    @Test
    fun register_auth() {
        CeresRegister(
            DbCeres(
                SQLiteConn(
                    File.createTempFile("prefix", "suffix").absolutePath
                )
            )
        ).run {
            register("ID", "AUTH")
            assertEquals("AUTH", authCode("ID"))
        }
    }
}