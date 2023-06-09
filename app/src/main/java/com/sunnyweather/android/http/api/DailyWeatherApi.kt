package com.sunnyweather.android.http.api

import com.hjq.http.annotation.HttpIgnore
import com.hjq.http.config.IRequestApi
import com.sunnyweather.android.http.Url

/**
 *    author : Chip
 *    time   : 2023/4/11
 *    desc   :
 */
class DailyWeatherApi() : IRequestApi {
    @HttpIgnore
    var lng = "0"

    @HttpIgnore
    var lat = "0"

    constructor(lng: String, lat: String) : this() {
        this.lng = lng
        this.lat = lat
    }

    override fun getApi(): String {
        return Url.getDailyWeather(lng, lat)
    }


}