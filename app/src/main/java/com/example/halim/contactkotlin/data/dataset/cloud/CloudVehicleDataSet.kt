package com.example.halim.contactkotlin.data.dataset.cloud

import com.example.halim.contactkotlin.data.dataset.VehicleDataSet
import com.example.halim.contactkotlin.data.dataset.cloud.rest.VehicleService
import com.example.halim.contactkotlin.data.model.NewCar
import com.example.halim.contactkotlin.data.model.UsedCar
import com.example.halim.contactkotlin.data.model.Vehicle
import io.reactivex.Observable


class CloudVehicleDataSet(private val vehicleService: VehicleService) : VehicleDataSet {

    override fun getNewCarDetails(carId: Long): Observable<NewCar> =
            vehicleService.getNewCarEngineDetails(carId)

    override fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar> =
            vehicleService.getUsedCarEngineDetails(carId)
}