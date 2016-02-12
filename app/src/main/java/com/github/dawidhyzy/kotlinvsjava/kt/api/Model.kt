package com.github.dawidhyzy.kotlinvsjava.kt.api

import com.google.gson.annotations.SerializedName

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 05/02/16.
 */

data class Clouds(val all: Int)
data class Coordinates(@SerializedName("lon") val longitude: Double,
                       @SerializedName("lat") val latitude: Double)
data class Main(@SerializedName("temp") val temperature: Double, val pressure: Double,
                val humidity: Double, @SerializedName("temp_min") val temperatureMin: Double,
                @SerializedName("temp_max") val temperatureMax: Double)
data class Rain(@SerializedName("3h") val _3h: Double)
data class Snow(@SerializedName("3h") val _3h: Double)
data class Sys(val type: Int, val id: Int, val message: Double, val country: String,
               val sunrise: Int, val sunset: Int)
data class Weather(val id: Int, val main: String, val description: String, val icon: String)
data class Wind(val speed: Double, @SerializedName("deg") val direction: Float)
data class Response(@SerializedName("coord") val coordinates: Coordinates, val weather: List<Weather>,
                    val base: String, val main: Main, val wind: Wind, val clouds: Clouds,
                    val rain: Rain, val snow: Snow, val dt: Int, val sys: Sys, val id: Int,
                    val name: String, val cod: Int)
