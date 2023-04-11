package com.sunnyweather.android.http.model

/**
 *    author : Chip
 *    time   : 2023/4/11
 *    desc   :
 */
data class Weather(val realtime: RealtimeResponse, val daily: DailyResponse)