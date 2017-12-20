package com.sarmady.contactkotlin.domain.entities

import android.os.Parcelable


open class Car(id: Long,
               desc: String?,
               makeYear: Int,
               price: Double,
               makeId: Long,
               modelId: Long,
               makeName: String?,
               modelName: String?,
               specs: List<Map<String, Any>>?,
               url: String?,
               images: List<Image?>?) :
        Vehicle(id, desc, makeYear, price, makeId, modelId,
                makeName, modelName, specs, url, images), Parcelable