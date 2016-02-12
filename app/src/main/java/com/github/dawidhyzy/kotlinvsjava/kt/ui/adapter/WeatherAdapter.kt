package com.github.dawidhyzy.kotlinvsjava.kt.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.github.dawidhyzy.kotlinvsjava.R
import com.github.dawidhyzy.kotlinvsjava.kt.api.Weather
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.d
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.inflate
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.loadUrl
import java.util.*

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {
    private val weatherList = ArrayList<Weather>(0)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_weather))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(weatherList[position]){
            holder.icon.loadUrl(generateIconUrl(icon));
            holder.description.text = "$main: $description"
        }

    }

    override fun getItemCount(): Int = this.weatherList.size

    fun setWeatherList(weatherList: List<Weather>) {
        this.weatherList.clear()
        this.weatherList.addAll(weatherList).let{
            d {"Objects added to list:$it; list size: ${this.weatherList.size}"}
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var icon: ImageView
        var description: TextView

        init {
            icon = itemView.findViewById(R.id.icon) as ImageView
            description = itemView.findViewById(R.id.description) as TextView
        }
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}