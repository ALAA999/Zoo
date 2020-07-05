package com.s.ajrami.zoo.model

import android.os.Parcel
import android.os.Parcelable

data class Fish(var id_fish: Int, var name_fish: String?, var image_fish: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_fish)
        parcel.writeString(name_fish)
        parcel.writeString(image_fish)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        const val TABLE_NAME = "Fish"
        const val COL_ID = "id"
        const val COL_NAME = "name_fish"
        const val COL_IMG = "image_fish"


        const val CREATE_TABLE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL, $COL_IMG INTEGER)"

        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}