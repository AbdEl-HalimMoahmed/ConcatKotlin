package com.sarmady.contactkotlin.domain.usecase.vehicles

import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import com.sarmady.contactkotlin.domain.usecase.UseCase
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor


abstract class VehicleUseCase<T, in Params : VehicleUseCase.Params>(protected val repository: VehiclesRepository,
                                                                    threadExecutor: ThreadExecutor, uiExecutor: PostExecutionThread) : UseCase<T, Params>(threadExecutor, uiExecutor) {

    sealed class Params {
        class GetVehicleDetails(val vehicleId: Long) : Params()
    }
}