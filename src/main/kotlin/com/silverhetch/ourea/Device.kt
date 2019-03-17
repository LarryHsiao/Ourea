package com.silverhetch.ourea

import java.net.InetAddress

interface Device {
    fun ip(): InetAddress
    fun isAlive(): Boolean
}