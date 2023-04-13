package com.sunnyweather.android.ui.place


import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjq.base.BaseAdapter
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppActivity
import com.sunnyweather.android.app.AppFragment
import com.sunnyweather.android.ui.weather.WeatherActivity
import kotlinx.android.synthetic.main.place_fragment.*


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject-Kotlin
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
class PlaceFragment : AppFragment<AppActivity>(), TextWatcher, BaseAdapter.OnItemClickListener {

    private val viewModel by lazy { ViewModelProvider(this).get(PlaceViewModel::class.java) }

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
        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        adapter = PlaceAdapter(requireContext())

        adapter.setOnItemClickListener(this)
        recyclerView?.adapter = adapter
        searchPlaceEdit?.addTextChangedListener(this)


    }

    override fun initData() {
        //对PlaceViewModel中的placeLiveData对象进行观察，当有任何数据变化时，就会回调到传入的Observer接口实现中
        viewModel.placeLiveData.observe(this) { result ->
            val places = result.getOrNull()
            if (places != null) {
                recyclerView?.visibility = View.VISIBLE
                bgImageView?.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)  //如果数据不为空，那么就将这些数据添加到PlaceViewModel的placeList集合中
                updateRecyclerView()    //通知刷新界面列表
            } else {
                toast("未能查询到任何地点")
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        return
    }

    override fun afterTextChanged(s: Editable?) {
        return
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        val placeName = s.toString()
        if (placeName.isNotEmpty()) {
            viewModel.searchPlaces(placeName)
        } else {
            recyclerView?.visibility = View.GONE
            bgImageView?.visibility = View.VISIBLE
            viewModel.placeList.clear()
            updateRecyclerView()
        }
    }

    private fun updateRecyclerView() {
        adapter.setData(viewModel.placeList)
    }

    override fun onItemClick(recyclerView: RecyclerView?, itemView: View?, position: Int) {
        val place = viewModel.placeList[position]
        val intent = Intent(getAttachActivity(), WeatherActivity::class.java).apply {
            putExtra("location_lng", place.location.lng)
            putExtra("location_lat", place.location.lat)
            putExtra("place_name", place.name)
        }
        startActivity(intent)
    }
}

