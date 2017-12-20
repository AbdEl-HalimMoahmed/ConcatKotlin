package com.sarmady.contactkotlin.data.dataset.cloud

import com.sarmady.contactkotlin.data.dataset.VehicleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.rest.VehicleService
import com.sarmady.contactkotlin.data.model.NewCar
import com.sarmady.contactkotlin.data.model.UsedCar
import io.reactivex.Observable


class CloudVehicleDataSet(private val vehicleService: VehicleService) : VehicleDataSet {

    override fun getNewCarDetails(carId: Long): Observable<NewCar> =
            vehicleService.getNewCarEngineDetails(carId)

    override fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar> =
            vehicleService.getUsedCarEngineDetails(carId)
}