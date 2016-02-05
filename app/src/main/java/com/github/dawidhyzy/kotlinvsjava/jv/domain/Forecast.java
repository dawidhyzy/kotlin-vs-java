package com.github.dawidhyzy.kotlinvsjava.jv.domain;

import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Response;
import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Weather;

import java.util.List;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 31/01/16.
 */
public class Forecast {

    private final double windSpeed;
    private final float windDirection;
    private String city;
    private String name;
    private double temperature;
    private float pressure;
    private int humidity;
    private List<Weather> weathersList;
    private String icon;

    public Forecast(String city, Response response) {
        this.city = city;
        name = response.getName();
        temperature = response.getMain().getTemperature();
        pressure = response.getMain().getPressure();
        humidity = response.getMain().getHumidity();
        windSpeed = response.getWind().getSpeed();
        windDirection = response.getWind().getDirection();
        weathersList = response.getWeather();
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return weathersList.get(0).getIcon();
    }

    public double getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public List<Weather> getWeathersList() {
        return weathersList;
    }
}