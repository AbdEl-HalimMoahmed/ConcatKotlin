package com.sarmady.contactkotlin.domain.usecase.vehicles

import com.sarmady.contactkotlin.domain.entities.NewCar
import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
import io.reactivex.Observable


class GetNewCarUseCase(repository: VehiclesRepository,
                       threadExecutor: ThreadExecutor, uiExecutor: PostExecutionThread)
    : VehicleUseCase<NewCar, VehicleUseCase.Params.GetVehicleDetails>(repository, threadExecutor, uiExecutor) {

    override fun buildUseCaseObservable(params: Params.GetVehicleDetails): Observable<NewCar>
            = repository.getNewCarDetails(params.vehicleId)
}