package com.sunnyweather.android.http.model

import com.google.gson.annotations.SerializedName

/**
 *    author : Chip
 *    time   : 2023/4/10
 *    desc   :
 */
data class PlaceResponse(
    val places: List<Place>,
    val query: String,
    val status: String
) {
    data class Place(
        @SerializedName("formatted_address") val address: String,
        val location: Location,
        val name: String,
    ) {
        data class Location(val lat: Double, val lng: Double)
    }
}