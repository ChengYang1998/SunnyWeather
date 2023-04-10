package com.sunnyweather.android.ui.place

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjq.http.listener.OnHttpListener
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppActivity
import com.sunnyweather.android.app.AppFragment
import com.sunnyweather.android.http.model.PlaceResponse
import com.sunnyweather.android.http.searchPlaces


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
class PlaceFragment : AppFragment<AppActivity>(), TextWatcher {


    private val mBgImageView: ImageView? by lazy { findViewById<ImageView>(R.id.bgImageView) }
    private val mActionBarLayout: FrameLayout? by lazy { findViewById<FrameLayout>(R.id.actionBarLayout) }
    private val mSearchPlaceEdit: EditText? by lazy { findViewById<EditText>(R.id.searchPlaceEdit) }
    private val mRecyclerView: RecyclerView? by lazy { findViewById<RecyclerView>(R.id.place_recyclerView) }

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
//        这个方法中先是给RecyclerView设置了
//        LayoutManager和适配器，并使用PlaceViewModel中的placeList集合作为数据源。紧接
//        着调用了EditText的addTextChangedListener() 方法来监听搜索框内容的变化情况 。 每当
//                搜索框中的内容发生了变化，我们就获取新的内容，然后传递给PlaceViewModel的
//        www.blogss.cn
//        searchPlaces() 方法 ， 这样就可以发起搜索城市数据的网络请求了 。 而当输入搜索框中的内
//                容为空时，我们就将RecyclerView隐藏起来，同时将那张仅用于美观用途的背景图显示出来。


        val layoutManager = LinearLayoutManager(activity)
        mRecyclerView?.layoutManager = layoutManager
        adapter = PlaceAdapter(requireContext())
        mRecyclerView?.adapter = adapter
        mSearchPlaceEdit?.addTextChangedListener(this)
    }


    override fun initData() {

    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        val placeName = s.toString()
        if (placeName.isNotEmpty()) {

            //搜索地点信息
            searchPlaces(requireActivity(), placeName, object : OnHttpListener<PlaceResponse?> {
                override fun onSucceed(result: PlaceResponse?) {
                    val places = result?.places
                    if (places != null) {
                        mRecyclerView?.visibility = View.VISIBLE
                        mBgImageView?.visibility = View.GONE
                        adapter.clearData()
                        adapter.addData(places as MutableList<PlaceResponse.Place>)
                    } else {
                        toast("未能查询到任何地点")
                    }

                }

                override fun onFail(e: Exception?) {
                    toast("查询error")
                }

            })

        } else {
            mRecyclerView?.visibility = View.GONE
            mBgImageView?.visibility = View.VISIBLE
            adapter.clearData()
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }


}

