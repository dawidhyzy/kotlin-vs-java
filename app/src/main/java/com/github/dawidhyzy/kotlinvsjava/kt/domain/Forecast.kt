package com.github.dawidhyzy.kotlinvsjava.kt.domain

import com.github.dawidhyzy.kotlinvsjava.kt.api.Response
import com.github.dawidhyzy.kotlinvsjava.kt.api.Weather

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
class Forecast(val city: String, response: Response) {

    val windSpeed: Double
    val windDirection: Float
    val name: String
    val temperature: Double
    val pressure: Double
    val humidity: Double
    val weathersList: List<Weather>

    init {
        name = response.name
        temperature = response.main.temperature
        pressure = response.main.pressure
        humidity = response.main.humidity
        windSpeed = response.wind.speed
        windDirection = response.wind.direction
        weathersList = response.weather
    }
}