package com.example.halim.contactkotlin.domain.repository

import com.example.halim.contactkotlin.domain.entities.NewCar
import com.example.halim.contactkotlin.domain.entities.UsedCar
import io.reactivex.Observable


interface VehiclesRepository {

    fun getNewCarDetails(carId: Long): Observable<NewCar>

    fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar>
}