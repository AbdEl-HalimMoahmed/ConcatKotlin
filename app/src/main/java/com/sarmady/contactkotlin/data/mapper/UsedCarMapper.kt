package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.VehicleImage
import com.sarmady.contactkotlin.domain.entities.Image
import com.sarmady.contactkotlin.data.model.UsedCar as UsedCarModel
import com.sarmady.contactkotlin.domain.entities.UsedCar as UsedCarEntity


class UsedCarMapper(private val imageMapper: Mapper<VehicleImage, Image>) : Mapper<UsedCarModel, UsedCarEntity>() {

    override fun transform(from: UsedCarModel?) = from?.let {
        UsedCarEntity(from.id, "", from.makeYear, from.price, from.makeId, from.modelId,
                from.makeName, from.modelName, arrayListOf(), from.url, imageMapper.transform(from.images))
    }
}