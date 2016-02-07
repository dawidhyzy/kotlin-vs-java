package com.github.dawidhyzy.kotlinvsjava.kt.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.github.dawidhyzy.kotlinvsjava.KotlinApp
import com.github.dawidhyzy.kotlinvsjava.R
import com.github.dawidhyzy.kotlinvsjava.kt.domain.Forecast
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.snack
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.toCelcius
import com.github.dawidhyzy.kotlinvsjava.kt.forecast.ForecastPresenter
import com.github.dawidhyzy.kotlinvsjava.kt.forecast.View
import com.github.dawidhyzy.kotlinvsjava.kt.ui.adapter.WeatherAdapter
import kotlinx.android.synthetic.main.activity_forecast.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.onClick
import timber.log.Timber
import java.util.*

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 30/01/16.
 */

class ForecastKotlinActivity : AppCompatActivity(), View{

    override fun showLoading(show: Boolean) {
        progress.isRefreshing = show;
    }

    override fun showError(message: String) {
        find<android.view.View>(android.R.id.content).snack(message){}
    }

    override fun setForecast(forecast: Forecast) {
        Timber.d("Get forecast")
        city_name.text = "${forecast.city}, ${forecast.name}"
        temperature.text = String.format(Locale.getDefault(),
                getString(R.string.temperature),
                forecast.temperature.toCelcius())
        pressure.text = String.format(Locale.getDefault(),
                getString(R.string.pressure),
                forecast.pressure)
        humidity.text = String.format(Locale.getDefault(),
                getString(R.string.humidity),
                forecast.humidity)
        wind.text = String.format(Locale.getDefault(), getString(R.string.wind),
                forecast.windSpeed,
                forecast.windDirection)
        (weather_list.adapter as WeatherAdapter).setWeatherList(forecast.weathersList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)
        initView()
    }

    private var presenter: ForecastPresenter = ForecastPresenter(this, KotlinApp.api)

    private val weatherAdapter: WeatherAdapter = WeatherAdapter();

    private fun initView(){
        setSupportActionBar(toolbar)

        refresh.onClick { presenter.refresh() }
        progress.isEnabled = false
        weather_list.layoutManager = LinearLayoutManager(this)
        weather_list.adapter = weatherAdapter

        presenter.loadForecast()
    }
}