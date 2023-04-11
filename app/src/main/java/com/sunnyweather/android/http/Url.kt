package com.sunnyweather.android.http

import com.sunnyweather.android.app.SunnyWeatherApplication

/**
 *    author : cy
 *    time   : 2023/4/10
 *    desc   :
 */

class Url {
    companion object {
        val SERVER_RELEASE = "https://api.caiyunapp.com"

        // 地址前缀
        val API_PREFIX = "/"

        //查询城市信息
        val SEARCH_PLACE = "v2/place"

        fun getRealtimeWeather(lng: String, lat: String): String {
            return "v2.6/${SunnyWeatherApplication.TOKEN}/${lng},${lat}/realtime"
        }

        fun getDailyWeather(lng: String, lat: String): String {
            return "v2.6/${SunnyWeatherApplication.TOKEN}/${lng},${lat}/daily                "
        }

    }

}
