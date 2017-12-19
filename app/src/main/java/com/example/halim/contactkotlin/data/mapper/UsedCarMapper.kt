package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.VehicleImage
import com.example.halim.contactkotlin.domain.entities.Image
import com.example.halim.contactkotlin.data.model.UsedCar as UsedCarModel
import com.example.halim.contactkotlin.domain.entities.UsedCar as UsedCarEntity


class UsedCarMapper(private val imageMapper: Mapper<VehicleImage, Image>) : Mapper<UsedCarModel, UsedCarEntity>() {

    override fun transform(from: UsedCarModel?): UsedCarEntity?
            = UsedCarEntity(from?.id ?: -1L, "", from?.makeYear ?: 0, from?.price ?: 0.0, from?.makeId ?: -1L, from?.modelId ?: -1L,
            from?.makeName, from?.modelName, arrayListOf(), from?.url, imageMapper.transform(from?.images))
}