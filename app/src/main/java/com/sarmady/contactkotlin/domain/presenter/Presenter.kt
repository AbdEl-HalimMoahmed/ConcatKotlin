package com.sarmady.contactkotlin.domain.presenter

import com.sarmady.contactkotlin.domain.bus.Bus
import com.sarmady.contactkotlin.domain.view.View


abstract class Presenter<out V : View>(private val bus: Bus, val view: V) {

    abstract fun register(bus: Bus)

    open fun init() {
        register(bus)
    }

    open fun dispose() {
        bus.unregister(this)
    }
}