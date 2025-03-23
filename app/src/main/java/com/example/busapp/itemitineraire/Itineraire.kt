package com.example.busapp.itemitineraire

import android.os.Parcel
import android.os.Parcelable

data class Itineraire(
    val source: String,
    val destination: String,
    val horaireDepart: String,
    val horaireArrivee: String,
    val arrets: String,
    val numbus: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(source)
        parcel.writeString(destination)
        parcel.writeString(horaireDepart)
        parcel.writeString(horaireArrivee)
        parcel.writeString(arrets)
        parcel.writeString(numbus)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Itineraire> {
        override fun createFromParcel(parcel: Parcel): Itineraire {
            return Itineraire(parcel)
        }

        override fun newArray(size: Int): Array<Itineraire?> {
            return arrayOfNulls(size)
        }
    }
}