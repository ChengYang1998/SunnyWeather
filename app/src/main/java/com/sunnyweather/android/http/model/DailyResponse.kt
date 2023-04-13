package com.sunnyweather.android.http.model

import com.google.gson.annotations.SerializedName

/**
 *    author : Chip
 *    time   : 2023/4/11
 *    desc   :
 */
data class DailyResponse(
    val result: Result,
    val status: String
) {
    data class Result(
        val daily: Daily
    ) {
        data class Daily(
            @SerializedName("life_index")
            val lifeIndex: LifeIndex,
            val skycon: List<Skycon>,
            val temperature: List<Temperature>
        ) {
            data class LifeIndex(
                val carWashing: List<LifeDescription>,
                val coldRisk: List<LifeDescription>,
                val dressing: List<LifeDescription>,
                val ultraviolet: List<LifeDescription>
            ) {
                data class LifeDescription(
                    val desc: String
                )
            }

            data class Skycon(
                val date: String,
                val value: String
            )

            data class Temperature(
                val max: Double,
                val min: Double
            )
        }
    }
}