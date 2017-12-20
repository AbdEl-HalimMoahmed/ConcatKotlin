package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.VehicleImage
import com.sarmady.contactkotlin.domain.entities.Image
import com.sarmady.contactkotlin.data.model.NewCar as NewCarModel
import com.sarmady.contactkotlin.domain.entities.NewCar as NewCarEntity


class NewCarMapper(private val imageMapper: Mapper<VehicleImage, Image>) : Mapper<NewCarModel, NewCarEntity>() {

    override fun transform(from: NewCarModel?) = from?.let {
        NewCarEntity(from.id, from.desc, from.makeYear, from.price, from.installments, from.makeId, from.modelId,
                from.makeName, from.modelName, from.specs?.mapNotNull { it }, from.url, from.colors?.mapNotNull { it },
                imageMapper.transform(from.images))
    }

}