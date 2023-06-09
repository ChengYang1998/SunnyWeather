package com.sunnyweather.android.logic

import androidx.lifecycle.liveData
import com.sunnyweather.android.http.SunnyWeatherNetwork.searchDailyInfo
import com.sunnyweather.android.http.SunnyWeatherNetwork.searchPlacesInfo
import com.sunnyweather.android.http.SunnyWeatherNetwork.searchRealtimeInfo
import com.sunnyweather.android.http.model.PlaceResponse
import com.sunnyweather.android.http.model.Weather
import com.sunnyweather.android.logic.dao.PlaceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext



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
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = searchPlacesInfo(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }


    /**
     * 协程方式
     */
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {

        coroutineScope {
            val deferredRealtime = async {
                searchRealtimeInfo(lng, lat)
            }
            val deferredDaily = async {
                searchDailyInfo(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(
                    realtimeResponse.result.realtime,
                    dailyResponse.result.daily
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" +
                                "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }

    }


    /**
     *  按照liveData()函数的参数接收标准定义的一个高阶函数
     *  在fire()函数的内部会先调用一下liveData()函数，然后在liveData()函数的代码块中统一进行了try catch处理，并在try语句中调用传入的Lambda表达式中的代码，最终获取Lambda表达式的执行结果并调用emit()方法发射出去。
     *  @目的 简化try catch处理
     */
    @Suppress("SameParameterValue")
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: PlaceResponse.Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
    fun clearPlace() {
        PlaceDao.clearPlace()
    }
}



