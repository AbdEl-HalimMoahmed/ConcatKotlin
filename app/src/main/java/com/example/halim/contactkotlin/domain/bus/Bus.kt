package com.example.halim.contactkotlin.domain.bus

interface Bus {
    fun post(event: Any)
    fun <T> register(lifeCycle: Any, eventType: Class<T>, action: (t:T) -> Unit)
    fun unregister(lifeCycle: Any)
}