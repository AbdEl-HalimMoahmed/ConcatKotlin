package com.sarmady.contactkotlin.data.model

import com.google.gson.annotations.SerializedName


open class Vehicle(@SerializedName("ID") val id: Long = 0,
                   @SerializedName("MakeYear") val makeYear: Int = 0,
                   @SerializedName("Price") val price: Double = 0.0,
                   @SerializedName("MakeID") val makeId: Long = 0,
                   @SerializedName("ModelID") val modelId: Long = 0,
                   @SerializedName("MakeName") val makeName: String = "",
                   @SerializedName("ModelName") val modelName: String = "",
                   @SerializedName("URL") val url: String = "",
                   @SerializedName("Gallery") val images: List<VehicleImage> = arrayListOf())

data class VehicleImage(@SerializedName("Small") val small: String,
                        @SerializedName("Large") val large: String)