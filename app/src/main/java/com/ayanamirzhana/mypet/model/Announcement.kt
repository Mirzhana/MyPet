package com.ayanamirzhana.mypet.model

import android.os.Parcel
import android.os.Parcelable

data class Announcement(var title: String,
                        var category: String,
                        var description: String,
                        var contact: String,
                        var imageUrl: String) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(category)
        parcel.writeString(description)
        parcel.writeString(contact)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Announcement> {
        override fun createFromParcel(parcel: Parcel): Announcement {
            return Announcement(parcel)
        }

        override fun newArray(size: Int): Array<Announcement?> {
            return arrayOfNulls(size)
        }
    }
}