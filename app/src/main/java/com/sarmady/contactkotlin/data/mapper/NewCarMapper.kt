package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.VehicleImage
import com.sarmady.contactkotlin.domain.entities.Image
import com.sarmady.contactkotlin.data.model.NewCar as NewCarModel
import com.sarmady.contactkotlin.domain.entities.NewCar as NewCarEntity


class NewCarMapper(private val imageMapper: Mapper<VehicleImage, Image>) : Mapper<NewCarModel, NewCarEntity>() {

    override fun transform(from: NewCarModel?): NewCarEntity?
            = NewCarEntity(from?.id ?: -1L, from?.desc, from?.makeYear ?: 0, from?.price ?: 0.0,
            from?.installments ?: 0.0, from?.makeId ?: -1L, from?.modelId ?: -1L,
            from?.makeName, from?.modelName, from?.specs, from?.url, from?.colors, imageMapper.transform(from?.images))

}