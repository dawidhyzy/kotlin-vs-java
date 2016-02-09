package com.github.dawidhyzy.kotlinvsjava.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.dawidhyzy.kotlinvsjava.KotlinApp
import com.github.dawidhyzy.kotlinvsjava.R
import com.github.dawidhyzy.kotlinvsjava.kt.domain.Forecast
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.contentView
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.d
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.snack
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.toCelcius
import com.github.dawidhyzy.kotlinvsjava.kt.forecast.ForecastPresenter
import com.github.dawidhyzy.kotlinvsjava.kt.forecast.View
import com.github.dawidhyzy.kotlinvsjava.kt.ui.adapter.WeatherAdapter
import kotlinx.android.synthetic.main.activity_forecast.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.onClick

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 30/01/16.
 */

class ForecastKotlinActivity : AppCompatActivity(), View{

    private var presenter: ForecastPresenter = ForecastPresenter(this, KotlinApp.api)

    private val weatherAdapter: WeatherAdapter by lazy { WeatherAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        initView()
    }

    private fun initView(){
        setSupportActionBar(toolbar)

        refresh.onClick { presenter.refresh() }
        progress.isEnabled = false
        weather_list.layoutManager = LinearLayoutManager(this)
        weather_list.adapter = weatherAdapter

        presenter.loadForecast()
    }

    override fun showLoading(show: Boolean) {
        progress.isRefreshing = show;
    }

    override fun showError(message: String) {
        contentView().snack(message){}
    }

    override fun setForecast(forecast: Forecast) {
        d {"Get forecast"}
        forecast.apply {
            city_name.text = "$city, $name"
            temperature_txt.text = getString(R.string.temperature, temperature.toCelcius())
            pressure_txt.text = getString(R.string.pressure, pressure)
            humidity_txt.text = getString(R.string.humidity, humidity)
            wind_txt.text = getString(R.string.wind, windSpeed, windDirection)
            (weather_list.adapter as WeatherAdapter).setWeatherList(weathersList)
        }
    }
}