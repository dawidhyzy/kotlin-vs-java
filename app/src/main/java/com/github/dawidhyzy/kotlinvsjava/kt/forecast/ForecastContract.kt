package com.github.dawidhyzy.kotlinvsjava.kt.forecast

import com.github.dawidhyzy.kotlinvsjava.kt.domain.Forecast

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
interface View {
    fun showLoading(show: Boolean)
    fun showError(message: String)
    fun setForecast(forecast: Forecast)
}

interface Presenter {
    fun loadForecast()
    fun refresh()
}