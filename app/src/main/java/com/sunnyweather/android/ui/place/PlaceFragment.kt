package com.sunnyweather.android.ui.place

import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppActivity
import com.sunnyweather.android.app.AppFragment

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
class PlaceFragment : AppFragment<AppActivity>() {


    private val mBgImageView: ImageView? by lazy { findViewById<ImageView>(R.id.bgImageView) }
    private val mActionBarLayout: FrameLayout? by lazy { findViewById<FrameLayout>(R.id.actionBarLayout) }
    private val mSearchPlaceEdit: EditText? by lazy { findViewById<EditText>(R.id.searchPlaceEdit) }
    private val mRecyclerView: RecyclerView? by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private lateinit var adapter: PlaceAdapter

    companion object {

        fun newInstance(): PlaceFragment {
            return PlaceFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.place_fragment
    }

    override fun initView() {


    }

    override fun initData() {}
}