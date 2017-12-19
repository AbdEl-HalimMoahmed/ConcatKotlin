package com.example.halim.contactkotlin.data.model

import com.google.gson.annotations.SerializedName


class NewCar(@SerializedName("Description") val desc: String,
             @SerializedName("Installment") val installments: Double,
             @SerializedName("Specs") val specs: List<Map<String, Any>>,
             @SerializedName("Colors") val colors: List<String>) : Vehicle()