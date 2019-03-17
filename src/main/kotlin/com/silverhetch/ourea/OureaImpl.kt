package com.silverhetch.ourea

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
    private val executor = Executors.newSingleThreadScheduledExecutor()
    // @todo 1 figure out why not working in development branch
    private val broadcastConn: TextBaseConn = BroadcastConn(
        DatagramSocket(),
        1024,
        ConstSource(InetAddress.getByName("255.255.255.255")),
        4521
    ) { inputPacket ->
        try {
            System.out.println(
                "Recieved: " + String(inputPacket.data)
            )
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