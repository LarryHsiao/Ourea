package com.silverhetch.ourea

import com.silverhetch.clotho.database.sqlite.SQLiteConn
import com.silverhetch.clotho.storage.DbCeres
import org.junit.Ignore
import org.junit.Test

class OureaTest {
    @Ignore
    @Test
    fun simple() {
        OureaImpl(
            DbCeres(
                SQLiteConn(
                    "ourea.db"
                )
            )
        ).run {
            init()
            addObserver { observable, data ->
                data.forEach { t, u ->

                    System.out.println(
                        """
                            Device:
                        ${u.ip()}

                """.trimIndent()
                    )
                }
            }
            Thread.sleep(100000)
            shutdown()
        }
    }
}