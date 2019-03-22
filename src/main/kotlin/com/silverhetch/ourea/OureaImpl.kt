package com.silverhetch.ourea

import com.silverhetch.clotho.connection.broadcast.BroadcastAddressCp
import com.silverhetch.clotho.connection.broadcast.BroadcastAddressFirst
import com.silverhetch.clotho.connection.broadcast.BroadcastConn
import com.silverhetch.clotho.connection.socket.TextBaseConn
import com.silverhetch.clotho.observable.ObservableImpl
import com.silverhetch.clotho.source.ConstSource
import java.net.DatagramSocket
import java.net.InetAddress
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.MILLISECONDS

class OureaImpl() : ObservableImpl<List<Device>>(ArrayList()), Ourea {
    companion object {
        private const val OUREA_PORT = 13232
        private const val PACKET_SIZE = 1024
    }

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val broadDest = BroadcastAddressFirst().value()
    private val broadcastConn: TextBaseConn = BroadcastConn(
        DatagramSocket(OUREA_PORT),
        PACKET_SIZE,
        ConstSource(broadDest ?: InetAddress.getByName("255.255.255.255")),
        OUREA_PORT
    ) { inputPacket ->
        try {
            if (inputPacket.address.toString() != broadDest.toString()){

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