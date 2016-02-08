package com.github.dawidhyzy.kotlinvsjava.kt.forecast

import com.github.dawidhyzy.kotlinvsjava.kt.api.OpenWeatherMapApi
import com.github.dawidhyzy.kotlinvsjava.kt.domain.Forecast
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.d
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.safe
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.onError
import rx.schedulers.Schedulers
import java.lang.ref.WeakReference

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
class ForecastPresenter(val view: View, val api: OpenWeatherMapApi) : Presenter {

    private var viewReference: WeakReference<View> = WeakReference(view)

    val city: String = "Kraków"

    override fun loadForecast() {
        d { "Loading weather for $city" }
        api.getWeatherByCityName(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewReference.safe { showLoading(true) } }
                .doOnCompleted { viewReference.safe { showLoading(false) } }
                .onError { e ->
                    viewReference.safe {
                        showLoading(false)
                    }
                }
                .map { Forecast(city, it) }
                .subscribe(
                    { forecast : Forecast ->
                        d { "Forecast loaded: $forecast" }
                        viewReference.safe { setForecast(forecast) }
                    },
                    { throwable : Throwable ->
                        throwable.printStackTrace()
                        viewReference.safe {
                            throwable.message?.let { showError(throwable.message as String) }
                        }
                    }

                )

    }

    override fun refresh() {
        d { "Refreshing weather for $city" }
        loadForecast()
    }
}