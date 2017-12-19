package com.example.halim.contactkotlin.domain.usecase.vehicles

import com.example.halim.contactkotlin.domain.repository.VehiclesRepository
import com.example.halim.contactkotlin.domain.usecase.UseCase
import com.halim.fashiononwheels.domain.excutor.PostExecutionThread
import com.halim.fashiononwheels.domain.excutor.ThreadExecutor


abstract class VehicleUseCase<T, in Params : VehicleUseCase.Params>(protected val repository: VehiclesRepository,
                                                                    threadExecutor: ThreadExecutor, uiExecutor: PostExecutionThread) : UseCase<T, Params>(threadExecutor, uiExecutor) {

    sealed class Params {
        class GetVehicleDetails(val vehicleId: Long) : Params()
    }
}