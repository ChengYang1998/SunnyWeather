package com.sunnyweather.android.http

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hjq.http.EasyHttp
import com.hjq.http.exception.ResultException
import com.hjq.http.exception.TokenException
import com.hjq.http.listener.HttpCallback
import com.hjq.http.listener.OnHttpListener
import com.sunnyweather.android.app.SunnyWeatherApplication
import com.sunnyweather.android.http.api.DailyWeatherApi
import com.sunnyweather.android.http.api.PlaceInfoApi
import com.sunnyweather.android.http.api.RealtimeWeatherApi
import com.sunnyweather.android.http.model.DailyResponse
import com.sunnyweather.android.http.model.PlaceResponse
import com.sunnyweather.android.http.model.RealtimeResponse
import com.sunnyweather.android.http.model.Weather

/**
 *    author : cy
 *    time   : 2023/4/10
 *    desc   :
 */


object Repository {

    /**
     *   搜索地点信息 livedata
     *   返回的LiveData对象是可供Activity观察的
     */
    fun searchPlaces(
        query: String
    ): LiveData<Result<List<PlaceResponse.Place>>> {
        val liveData = MutableLiveData<Result<List<PlaceResponse.Place>>>()

        EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
            .api(PlaceInfoApi().apply {
                setQuery(query)
                setToken(SunnyWeatherApplication.TOKEN)
            })
            .request(object : HttpCallback<PlaceResponse>(object : OnHttpListener<PlaceResponse> {

                override fun onSucceed(result: PlaceResponse) {
                    if (result.status == "ok") {
                        // 请求成功的操作
                        liveData.value = Result.success(result.places)
                    } else if (result.status == "failure") {
                        // 登录失效的操作
                        liveData.value = Result.failure(TokenException("Token 失效，请重新登录"))
                    } else {
                        // 请求失败的操作
                        liveData.value =
                            Result.failure(ResultException(result.status, result))
                    }
                }

                override fun onFail(e: Exception) {
                    // 请求失败的操作
                    liveData.value = Result.failure(e)
                }


            }) {})

        return liveData
    }


    fun refreshWeather(lng: String, lat: String): LiveData<Result<Weather>> {

        val liveData = MutableLiveData<Result<Weather>>()
        var dailyResponse: DailyResponse? = null
        var realtimeResponse: RealtimeResponse? = null



        EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
            .api(DailyWeatherApi(lng, lat))
            .request(object : HttpCallback<DailyResponse>(object : OnHttpListener<DailyResponse> {
                override fun onSucceed(result: DailyResponse) {
                    if (result.status == "ok") {
                        dailyResponse = result

                        EasyHttp.get(SunnyWeatherApplication.AppLifecycleOwner)
                            .api(RealtimeWeatherApi(lng, lat))
                            .request(object : HttpCallback<RealtimeResponse>(object :
                                OnHttpListener<RealtimeResponse> {
                                override fun onSucceed(result: RealtimeResponse) {
                                    if (result.status == "ok") {
                                        realtimeResponse = result




                                    }
                                }

                                override fun onFail(e: Exception) {
                                    throw e
                                }

                            }) {})

                    }
                }

                override fun onFail(e: Exception) {
                    // 请求失败的操作
                    throw e
                }
            }) {})

        return liveData
    }


}