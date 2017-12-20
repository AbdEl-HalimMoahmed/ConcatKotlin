package com.sarmady.contactkotlin.domain.usecase.vehicles

import com.sarmady.contactkotlin.domain.entities.UsedCar
import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
import io.reactivex.Observable


class GetUsedCarUseCase(repository: VehiclesRepository,
                        threadExecutor: ThreadExecutor, uiExecutor: PostExecutionThread)
    : VehicleUseCase<UsedCar, VehicleUseCase.Params.GetVehicleDetails>(repository, threadExecutor, uiExecutor) {

    override fun buildUseCaseObservable(params: Params.GetVehicleDetails): Observable<UsedCar>
            = repository.getUsedCarEngineDetails(params.vehicleId)
}