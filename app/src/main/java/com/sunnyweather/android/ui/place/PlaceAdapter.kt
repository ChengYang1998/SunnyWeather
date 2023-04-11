package com.sunnyweather.android.ui.place

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import com.sunnyweather.android.R
import com.sunnyweather.android.app.AppAdapter
import com.sunnyweather.android.http.model.PlaceResponse

/**
 *    author : Chip
 *    time   : 2023/4/10
 *    desc   :
 */
class PlaceAdapter constructor(context: Context) :
    AppAdapter<PlaceResponse.Place>(context) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder()
    }

    inner class ViewHolder : AppViewHolder(R.layout.place_item) {
        private val placeName: TextView? by lazy { findViewById(R.id.placeName) }
        private val placeAddress: TextView? by lazy { findViewById<TextView>(R.id.placeAddress) }

        override fun onBindView(position: Int) {
            val place: PlaceResponse.Place = getItem(position)

            placeName?.text = place.name
            placeAddress?.text = place.address

        }
    }
}