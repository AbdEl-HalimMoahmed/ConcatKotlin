package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.VehicleImage
import com.example.halim.contactkotlin.domain.entities.Image


class VechileIMageMapper : Mapper<VehicleImage, Image>() {

    override fun transform(from: VehicleImage?): Image?
            = Image(-1L, from?.small, from?.large)
}