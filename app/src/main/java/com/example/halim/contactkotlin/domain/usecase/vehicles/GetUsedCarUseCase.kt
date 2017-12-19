package com.example.halim.contactkotlin.domain.usecase.vehicles

import com.example.halim.contactkotlin.domain.entities.UsedCar
import com.example.halim.contactkotlin.domain.repository.VehiclesRepository
import com.halim.fashiononwheels.domain.excutor.PostExecutionThread
import com.halim.fashiononwheels.domain.excutor.ThreadExecutor
import io.reactivex.Observable


class GetUsedCarUseCase(repository: VehiclesRepository,
                        threadExecutor: ThreadExecutor, uiExecutor: PostExecutionThread)
    : VehicleUseCase<UsedCar, VehicleUseCase.Params.GetVehicleDetails>(repository, threadExecutor, uiExecutor) {

    override fun buildUseCaseObservable(params: Params.GetVehicleDetails): Observable<UsedCar>
            = repository.getUsedCarEngineDetails(params.vehicleId)
}