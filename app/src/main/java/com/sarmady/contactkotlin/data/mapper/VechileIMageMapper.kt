package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.VehicleImage
import com.sarmady.contactkotlin.domain.entities.Image


class VechileIMageMapper : Mapper<VehicleImage, Image>() {

    override fun transform(from: VehicleImage?): Image?
            = Image(-1L, from?.small, from?.large)
}