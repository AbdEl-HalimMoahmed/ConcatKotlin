package com.example.halim.contactkotlin.domain.entities

import android.os.Parcelable


class NewCar(id: Long = 0,
             desc: String? = null,
             makeYear: Int = 0,
             price: Double = 0.0,
             val installments: Double = 0.0,
             makeId: Long = 0,
             modelId: Long = 0,
             makeName: String? = null,
             modelName: String? = null,
             specs: List<Map<String, Any>>? = null,
             url: String? = null,
             val colors: List<String>? = null,
             images: List<Image?>? = null) : Car(id, desc, makeYear, price, makeId, modelId,
        makeName, modelName, specs, url, images), Parcelable