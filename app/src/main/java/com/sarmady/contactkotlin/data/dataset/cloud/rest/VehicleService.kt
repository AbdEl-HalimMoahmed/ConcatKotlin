package com.sarmady.contactkotlin.data.dataset.cloud.rest

import com.sarmady.contactkotlin.data.model.NewCar
import com.sarmady.contactkotlin.data.model.UsedCar
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface VehicleService {

    @GET("/newcars/engine")
    fun getNewCarEngineDetails(@Query("engID") engineId: Long): Observable<NewCar>

    @GET("/usedcars/engine")
    fun getUsedCarEngineDetails(@Query("ucID") engineId: Long): Observable<UsedCar>
}