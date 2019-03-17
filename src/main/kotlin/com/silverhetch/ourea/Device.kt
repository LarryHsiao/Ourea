package com.silverhetch.ourea

interface Device {
    fun ip(): String
    fun isAlive(): Boolean
}