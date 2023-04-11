package com.sunnyweather.android.ui.place


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sunnyweather.android.http.model.PlaceResponse
import com.sunnyweather.android.logic.Repository

/**
 *    author : Chip
 *    time   : 2023/4/11
 *    desc   :
 */
class PlaceViewModel : ViewModel() {

    private val searchLiveData = MutableLiveData<String>()


    /**
     * 用于对界面上显示的城市数据进行缓存，因为原则上与界面相关的数据都应该放到ViewModel中
     */
    val placeList = ArrayList<PlaceResponse.Place>()


    /**
     * 需要传入具备生命周期感知能力的 Context 子类，例如 Activity 和 Fragment。
     * 因此，通常情况下，我们会将 Activity 或 Fragment 作为 LifecycleOwner 的实现类来使用。
     */
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
}