package com.sunnyweather.android.http

import androidx.lifecycle.LifecycleOwner
import com.hjq.http.EasyHttp
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.sunnyweather.android.app.SunnyWeatherApplication
import com.sunnyweather.android.http.api.PlaceInfoApi
import com.sunnyweather.android.http.model.PlaceResponse

/**
 *    author : cy
 *    time   : 2023/4/10
 *    desc   :
 */

/**
 *   搜索地点信息
 */
fun searchPlaces(
    lifecycleOwner: LifecycleOwner,
    place: String,
    onHttpListener: OnHttpListener<PlaceResponse?>
) {
    // EasyHttp请求地点信息
    EasyHttp.get(lifecycleOwner)
        .api(PlaceInfoApi().apply {
            setQuery(place)
            setToken(SunnyWeatherApplication.TOKEN)

        })
        .request(object : HttpCallback<PlaceResponse?>(onHttpListener) {

        })
}

