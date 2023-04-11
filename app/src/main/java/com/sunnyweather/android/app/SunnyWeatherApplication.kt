package com.sunnyweather.android.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner

/**
 *    author : Chip
 *    time   : 2023/4/10
 *    desc   :
 */
class SunnyWeatherApplication : AppApplication() {

    companion object {
        //彩云天气的令牌
        const val TOKEN = "Uv6iF6RG6IsDjFKE"

        //全局获取Context上下文
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

         val AppLifecycleOwner =   ProcessLifecycleOwner.get()


    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

    }

}