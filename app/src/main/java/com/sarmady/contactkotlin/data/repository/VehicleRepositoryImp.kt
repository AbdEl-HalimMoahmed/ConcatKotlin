package com.sarmady.contactkotlin.data.repository

import com.sarmady.contactkotlin.data.dataset.VehicleDataSet
import com.sarmady.contactkotlin.data.mapper.Mapper
import com.sarmady.contactkotlin.domain.entities.UsedCar
import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import io.reactivex.Observable
import com.sarmady.contactkotlin.data.model.NewCar as NewCarModel
import com.sarmady.contactkotlin.domain.entities.NewCar as NewCarEntity
import com.sarmady.contactkotlin.data.model.UsedCar as UsedCarModel
import com.sarmady.contactkotlin.domain.entities.UsedCar as UsedCarEntity


class VehicleRepositoryImp(private val vehicleDataSet: VehicleDataSet,
                           private val newCarMapper: Mapper<NewCarModel, NewCarEntity>,
                           private val usedCarMapper: Mapper<UsedCarModel, UsedCarEntity>) : VehiclesRepository {

    override fun getNewCarDetails(carId: Long): Observable<NewCarEntity>
            = vehicleDataSet.getNewCarDetails(carId).map { newCarMapper.transform(it) ?: NewCarEntity() }

    override fun getUsedCarEngineDetails(carId: Long): Observable<UsedCar>
            = vehicleDataSet.getUsedCarEngineDetails(carId).map { usedCarMapper.transform(it) ?: UsedCarEntity() }
}