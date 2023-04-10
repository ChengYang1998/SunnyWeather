package com.sunnyweather.android.ui.place

import androidx.fragment.app.FragmentContainerView
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppActivity

class MainActivity : AppActivity() {

    private val mPlaceFragment: FragmentContainerView? by lazy { findViewById<FragmentContainerView>(R.id.placeFragment) }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {


    }

    override fun initData() {

    }
}