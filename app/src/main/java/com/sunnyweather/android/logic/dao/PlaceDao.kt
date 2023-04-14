package com.sunnyweather.android.logic.dao

import android.content.Context

import com.google.gson.Gson
import com.sunnyweather.android.app.SunnyWeatherApplication
import com.sunnyweather.android.http.model.PlaceResponse

/**
 *    author : Chip
 *    time   : 2023/4/13
 *    desc   :
 */
object PlaceDao {

    fun savePlace(place: PlaceResponse.Place) {
        sharedPreferences().edit().putString("place", Gson().toJson(place)).apply()
    }

    fun getSavedPlace(): PlaceResponse.Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, PlaceResponse.Place::class.java)
    }

    fun isPlaceSaved(): Boolean {

        val value = sharedPreferences().getString("place", null)
        return value != null
    }

    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun clearPlace() {
        sharedPreferences().edit().clear().apply()
    }
}


