package com.s.ajrami.zoo.model

import android.os.Parcel
import android.os.Parcelable

data class Bird (var id_bird:Int,var name_bird:String,var image_bird:String) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id_bird)
        parcel.writeString(name_bird)
        parcel.writeString(image_bird)
}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Animal> {
        const val TABLE_NAME = "Bird"
        const val COL_ID = "id_bird"
        const val COL_NAME = "name_bird"
        const val COL_IMG = "image_bird"

        const val CREATE_TABLE =
            "create table $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COL_NAME TEXT NOT NULL, $COL_IMG INTEGER )"

        override fun createFromParcel(parcel: Parcel): Animal {
            return Animal (parcel)
        }

        override fun newArray(size: Int): Array<Animal?> {
            return arrayOfNulls(size)
        }
    }

}