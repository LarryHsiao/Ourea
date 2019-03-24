package com.silverhetch.ourea

import com.silverhetch.clotho.observable.Observable

/**
 * Main object of Ourea.
 */
interface Ourea : Observable<Map<String,Device>> {
    fun init()
    fun shutdown()
}