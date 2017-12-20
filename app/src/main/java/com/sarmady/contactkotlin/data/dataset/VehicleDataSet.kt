package com.sarmady.contactkotlin.data.dataset

import com.sarmady.contactkotlin.data.model.NewCar
import com.sarmady.contactkotlin.data.model.UsedCar
import io.reactivex.Observable


interface VehicleDataSet {

    fun getNewCarDetails(carId: Long): Observable<NewCar>

    fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar>
}