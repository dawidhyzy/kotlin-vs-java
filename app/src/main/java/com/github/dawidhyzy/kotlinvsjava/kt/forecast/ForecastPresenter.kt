package com.github.dawidhyzy.kotlinvsjava.kt.forecast

import com.github.dawidhyzy.kotlinvsjava.kt.api.OpenWeatherMapApi
import com.github.dawidhyzy.kotlinvsjava.kt.domain.Forecast
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.onError
import rx.schedulers.Schedulers
import timber.log.Timber

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
class ForecastPresenter(val view: View, val api: OpenWeatherMapApi) : Presenter {

    val city: String = "Kraków"

    override fun loadForecast() {
        Timber.d("Loading weather for $city")
        api.getWeatherByCityName(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnCompleted { view.showLoading(false) }
                .onError { e ->
                    view.showLoading(false)
                }
                .map { Forecast(city, it) }
                .subscribe(
                    {forecast : Forecast ->
                        Timber.d("Forecast loaded: $forecast")
                        view.setForecast(forecast)
                    },
                    {throwable : Throwable -> throwable.printStackTrace()}

                )

    }

    override fun refresh() {
        Timber.d("Refreshing weather for $city")
        loadForecast()
    }
}