package com.s.ajrami.zoo.model

import android.os.Parcel
import android.os.Parcelable

data class Animal(var id_animal: Int, var name_animal: String, var image_animal: String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_animal)
        parcel.writeString(name_animal)
        parcel.writeString(image_animal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        const val TABLE_NAME = "Animal"
        const val COL_ID = "id"
        const val COL_NAME = "name_animal"
        const val COL_IMG = "image_animal"

        const val CREATE_TABLE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL, $COL_IMG INTEGER )"

        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal(parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}
