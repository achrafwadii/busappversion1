package com.example.busapp

import android.os.Parcel
import android.os.Parcelable

data class Route(
    val id: String = "",         // Identifiant de la route
    val numbus: String = "",     // Numéro du bus
    val dep: String = "",        // Départ
    val des: String = "",        // Destination
    val heuredep: String = "",  // Heure de départ
    val heuredes: String = "",  // Heure d'arrivée
    val pointarret: String = ""  // Points d'arrêt
) : Parcelable {

    // Constructeur utilisé pour créer un objet à partir d'un Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

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