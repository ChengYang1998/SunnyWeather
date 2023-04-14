package com.sunnyweather.android.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppActivity
import com.sunnyweather.android.http.model.Weather
import com.sunnyweather.android.http.model.getSky
import com.sunnyweather.android.ui.place.MainActivity
import kotlinx.android.synthetic.main.forecast.*
import kotlinx.android.synthetic.main.life_index.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.weather_activity.*
import kotlinx.android.synthetic.main.weather_activity.view.*
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


private const val FIRST_DAY = 0

class WeatherActivity : AppActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }

    override fun getLayoutId(): Int {
        return R.layout.weather_activity
    }

    override fun initView() {
        swipeRefresh.swipeRefresh.setEnableLoadMore(false)//是否启用上拉加载功能
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
        navBtn.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        super.onClick(view)
        if (view == navBtn) {
            viewModel.clearPlace()
            startActivity(MainActivity::class.java)
        }
    }

    override fun initData() {
        //从Intent中取出经纬度坐标和地区名称，并赋值到WeatherViewModel的相应变量中
        if (viewModel.locationLng.isEmpty()) {
            viewModel.locationLng = intent.getStringExtra("location_lng") ?: ""
        }
        if (viewModel.locationLat.isEmpty()) {
            viewModel.locationLat = intent.getStringExtra("location_lat") ?: ""
        }
        if (viewModel.placeName.isEmpty()) {
            viewModel.placeName = intent.getStringExtra("place_name") ?: ""
        }

        viewModel.weatherLiveData.observe(this) { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                toast("无法成功获取天气信息")
                result.exceptionOrNull()?.printStackTrace()
            }
            swipeRefresh.finishRefresh()
        }
        refreshWeather()

    }

    private fun refreshWeather() {
        viewModel.refreshWeather(viewModel.locationLng, viewModel.locationLat)
    }

    /**
     * 解析与展示
     */
    private fun showWeatherInfo(weather: Weather) {
        placeName.text = viewModel.placeName
        val realtime = weather.realtime
        val daily = weather.daily
        // 填充now.xml布局中的数据
        val currentTempText = "${realtime.temperature.toInt()} ℃"
        currentTemp.text = currentTempText
        currentSky.text = getSky(realtime.skycon).info
        val currentPM25Text = "空气指数 ${realtime.airQuality.aqi.chn.toInt()}"
        currentAQI.text = currentPM25Text
        nowLayout.setBackgroundResource(getSky(realtime.skycon).bg)
        // 填充forecast.xml布局中的数据
        forecastLayout.removeAllViews()
        val days = daily.skycon.size
        //使用了一个for-in循环来处理每天的天气信息
        for (i in FIRST_DAY until days) {
            val skycon = daily.skycon[i]
            val temperature = daily.temperature[i]
            val view = LayoutInflater.from(this).inflate(
                R.layout.forecast_item,
                forecastLayout, false
            )
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val skyInfo = view.findViewById(R.id.skyInfo) as TextView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView
            //解析时间
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmxxx")
            val dateTime = ZonedDateTime.parse(skycon.date, formatter)
            dateInfo.text = dateTime.format(formatter).subSequence(0, 10)

            val sky = getSky(skycon.value)
            skyIcon.setImageResource(sky.icon)
            skyInfo.text = sky.info
            val tempText = "${temperature.min.toInt()} ~ ${temperature.max.toInt()} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
        // 填充life_index.xml布局中的数据
        val lifeIndex = daily.lifeIndex
        coldRiskText.text = lifeIndex.coldRisk[FIRST_DAY].desc
        dressingText.text = lifeIndex.dressing[FIRST_DAY].desc
        ultravioletText.text = lifeIndex.ultraviolet[FIRST_DAY].desc
        carWashingText.text = lifeIndex.carWashing[FIRST_DAY].desc
        weatherLayout.visibility = View.VISIBLE
    }
}