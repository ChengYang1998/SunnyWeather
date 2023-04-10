package com.sunnyweather.android.http.api

import com.hjq.http.config.IRequestApi
import com.sunnyweather.android.http.Url

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2019/12/07
 *    desc   : 可进行拷贝的副本
 */
class PlaceInfoApi : IRequestApi {

    override fun getApi(): String {
        return Url.SEARCH_PLACE
    }

    /** token令牌 */
    private var token: String? = null

    /** place 需要查询的地点 */
    private var query: String? = null

    fun setToken(string: String): PlaceInfoApi = apply {
        this.token = string
    }

    fun setQuery(string: String): PlaceInfoApi = apply {
        this.query = string
    }


}