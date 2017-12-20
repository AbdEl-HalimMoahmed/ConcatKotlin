package com.sarmady.contactkotlin.domain.repository

import com.sarmady.contactkotlin.domain.entities.NewCar
import com.sarmady.contactkotlin.domain.entities.UsedCar
import io.reactivex.Observable


interface VehiclesRepository {

    fun getNewCarDetails(carId: Long): Observable<NewCar>

    fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar>
}