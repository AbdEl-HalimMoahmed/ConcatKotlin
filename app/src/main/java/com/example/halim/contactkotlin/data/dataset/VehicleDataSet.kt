package com.example.halim.contactkotlin.data.dataset

import com.example.halim.contactkotlin.data.model.NewCar
import com.example.halim.contactkotlin.data.model.UsedCar
import com.example.halim.contactkotlin.data.model.Vehicle
import io.reactivex.Observable


interface VehicleDataSet {

    fun getNewCarDetails(carId: Long): Observable<NewCar>

    fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar>
}