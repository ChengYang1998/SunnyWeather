package com.sunnyweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.http.model.PlaceResponse.Place.Location
import com.sunnyweather.android.logic.Repository

/**
 *    author : Chip
 *    time   : 2023/4/12
 *    desc   :
 */
class WeatherViewModel : ViewModel() {

    //可以被修改的 LiveData 对象 用于存储当前位置信息
    private val locationLiveData = MutableLiveData<Location>()

    //这些变量可以在 ViewModel 和 UI 界面之间传递数据，实现数据的共享和同步更新
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    //根据 locationLiveData 的值来自动刷新天气信息
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

    fun clearPlace()= Repository.clearPlace()
}