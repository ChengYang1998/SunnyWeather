package com.sunnyweather.android.http.api

import com.hjq.http.config.IRequestApi
import com.sunnyweather.android.http.Url

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/07
 *    desc   : 可进行拷贝的副本
 */
class RealtimeWeatherApi() : IRequestApi {

    var lng = "0"
    var lat = "0"

    constructor(lng: String, lat: String) : this() {
        this.lng = lng
        this.lat = lat
    }


    override fun getApi(): String {
        return Url.getRealtimeWeather(lng, lat)
    }


}