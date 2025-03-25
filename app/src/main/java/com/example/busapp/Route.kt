package com.example.busapp

import android.os.Parcel
import android.os.Parcelable

// Route.kt
@Parcelize
data class Route(
    val id: String = "",
    val numbus: String = "",
    val dep: String = "",
    val des: String = "",
    val heuredep: String = "",
    val heuredes: String = "",
    val pointarret: String = ""  // Champ pour les points d'arrêt
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(numbus)
        parcel.writeString(dep)
        parcel.writeString(des)
        parcel.writeString(heuredep)
        parcel.writeString(heuredes)
        parcel.writeString(pointarret)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Route> {
        override fun createFromParcel(parcel: Parcel): Route {
            return Route(parcel)
        }

        override fun newArray(size: Int): Array<Route?> {
            return arrayOfNulls(size)
        }
    }
}