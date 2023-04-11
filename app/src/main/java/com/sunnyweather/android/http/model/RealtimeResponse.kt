package com.sunnyweather.android.http.model

import com.google.gson.annotations.SerializedName

/**
 *    author : Chip
 *    time   : 2023/4/11
 *    desc   :
 */
data class RealtimeResponse(
    val result: Result,
    val status: String
) {
    data class Result(
        val realtime: Realtime
    ) {
        data class Realtime(
            @SerializedName("air_quality")
            val airQuality: AirQuality,

            val skycon: String,

            val temperature: Double
        ) {
            data class AirQuality(val aqi: Aqi)

            data class Aqi(val chn: Double)

        }
    }
}