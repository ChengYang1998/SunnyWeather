package com.sunnyweather.android.http

import com.hjq.http.EasyHttp
import com.hjq.http.model.ResponseClass
import com.sunnyweather.android.app.SunnyWeatherApplication
import com.sunnyweather.android.http.api.DailyWeatherApi
import com.sunnyweather.android.http.api.PlaceInfoApi
import com.sunnyweather.android.http.api.RealtimeWeatherApi
import com.sunnyweather.android.http.model.DailyResponse
import com.sunnyweather.android.http.model.PlaceResponse
import com.sunnyweather.android.http.model.RealtimeResponse

/**
 *    author : cy
 *    time   : 2023/4/11
 *    desc   : 统一的网络数据源访问入口
 */
object SunnyWeatherNetwork {


    fun searchPlacesInfo(query: String):PlaceResponse {
        return EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
            .api(PlaceInfoApi().setQuery(query))
            .execute(object : ResponseClass<PlaceResponse>() {})
    }

    fun searchRealtimeInfo(lng: String, lat: String): RealtimeResponse {

        return EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
            .api(RealtimeWeatherApi(lng, lat))
            .execute(object : ResponseClass<RealtimeResponse>() {})
    }

    fun searchDailyInfo(lng: String, lat: String): DailyResponse {

        return EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
            .api(DailyWeatherApi(lng, lat))
            .execute(object : ResponseClass<DailyResponse>() {})
    }


}