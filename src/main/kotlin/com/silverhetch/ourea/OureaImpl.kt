package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.BroadcastAddressFirst
import com.silverhetch.clotho.connection.broadcast.BroadcastConn
import com.silverhetch.clotho.connection.socket.TextBaseConn
import com.silverhetch.clotho.database.sqlite.SQLiteConn
import com.silverhetch.clotho.observable.ObservableImpl
import com.silverhetch.clotho.source.ConstSource
import com.silverhetch.clotho.storage.Ceres
import com.silverhetch.clotho.storage.DbCeres
import com.silverhetch.ourea.register.CeresRegister
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.MILLISECONDS

class OureaImpl(private val ceres: Ceres) : ObservableImpl<Map<String, Device>>(HashMap()), Ourea {
    companion object {
        private const val OUREA_PORT = 13232
        private const val PACKET_SIZE = 1024
    }
    private val deviceMap = HashMap<String, Device>()
    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val broadDest = BroadcastAddressFirst().value()
    private val broadcastConn: TextBaseConn = BroadcastConn(
        DatagramSocket(OUREA_PORT),
        PACKET_SIZE,
        ConstSource(broadDest?.broadcastAddress() ?: InetAddress.getByName("255.255.255.255")),
        OUREA_PORT
    ) { inputPacket ->
        try {
            broadDest?.run {
                if (inputPacket.address.toString() != broadDest.interfaceInetAddress().toString()) {
                    // @todo #1 device recognition
                    // @todo #2 device authorization
                    deviceMap[this.interfaceInetAddress().toString()] = DeviceImpl(
                        CeresRegister(ceres),
                        inputPacket.address
                    )
                    value = deviceMap
                    notifyObservers(value)
                }
            }
        } catch (ignore: Exception) {
            // ignore packet we don`t recognized.
        }
    }

    override fun init() {
        broadcastConn.launch()
        executor.scheduleAtFixedRate({
            broadcastConn.send("ping")
        }, 1000, 1000, MILLISECONDS)
    }

    override fun shutdown() {
        broadcastConn.close()
        executor.shutdownNow()
    }
}