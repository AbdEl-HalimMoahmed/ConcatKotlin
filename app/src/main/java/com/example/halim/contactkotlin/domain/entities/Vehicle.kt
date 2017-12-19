package com.example.halim.contactkotlin.domain.entities

import android.os.Parcel
import android.os.Parcelable
import com.example.halim.contactkotlin.domain.util.addMap
import com.example.halim.contactkotlin.domain.util.getMap


open class Vehicle(id: Long,
                   val desc: String?,
                   val makeYear: Int,
                   val price: Double,
                   val makeId: Long,
                   val modelId: Long,
                   val makeName: String?,
                   val modelName: String?,
                   val specs: List<Map<String, Any>>?,
                   val url: String?,
                   val images: List<Image?>?) : Entity(id), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readLong(),
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            arrayListOf(),
            parcel.readString(),
            parcel.createTypedArrayList(Image))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        super.writeToParcel(parcel, flags)
        parcel.writeString(desc)
        parcel.writeInt(makeYear)
        parcel.writeDouble(price)
        parcel.writeLong(makeId)
        parcel.writeLong(modelId)
        parcel.writeString(makeName)
        parcel.writeString(modelName)
        parcel.writeString(url)
        parcel.writeTypedList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }
}