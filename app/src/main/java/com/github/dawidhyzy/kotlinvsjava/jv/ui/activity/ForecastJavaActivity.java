package com.github.dawidhyzy.kotlinvsjava.jv.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.github.dawidhyzy.kotlinvsjava.App;
import com.github.dawidhyzy.kotlinvsjava.R;
import com.github.dawidhyzy.kotlinvsjava.jv.domain.Forecast;
import com.github.dawidhyzy.kotlinvsjava.jv.forecast.ForecastContract;
import com.github.dawidhyzy.kotlinvsjava.jv.forecast.ForecastPresenter;
import com.github.dawidhyzy.kotlinvsjava.jv.ui.adapter.WeatherAdapter;
import com.github.dawidhyzy.kotlinvsjava.jv.util.UnitsUtils;

import java.util.Locale;

public class ForecastJavaActivity extends AppCompatActivity implements ForecastContract.View {

    private SwipeRefreshLayout progress;
    private TextView cityName, temperature, pressure, humidity, wind;
    private WeatherAdapter weatherAdapter = new WeatherAdapter();

    private ForecastContract.Presenter presenter = new ForecastPresenter(this, App.getApi());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton refresh = (FloatingActionButton) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                presenter.refresh();
            }
        });
        cityName = (TextView) findViewById(R.id.city_name);
        temperature = (TextView) findViewById(R.id.temperature);
        pressure = (TextView) findViewById(R.id.pressure);
        humidity = (TextView) findViewById(R.id.humidity);
        wind = (TextView) findViewById(R.id.wind);
        progress = (SwipeRefreshLayout) findViewById(R.id.progress);
        progress.setEnabled(false);
        RecyclerView weatherList = (RecyclerView) findViewById(R.id.weather_list);
        weatherList.setLayoutManager(new LinearLayoutManager(this));
        weatherList.setAdapter(weatherAdapter);

        presenter.loadForecast();
    }

    @Override
    public void showLoading(boolean show) {
        progress.setRefreshing(show);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @SuppressLint("StringFormatMatches")
    @Override
    public void setForecast(Forecast forecast) {
        cityName.setText(String.format("%s, %s", forecast.getCity(), forecast.getName()));
        temperature.setText(String.format(Locale.getDefault(),
                getString(R.string.temperature),
                UnitsUtils.kelvinToCelsius(forecast.getTemperature())));
        pressure.setText(String.format(Locale.getDefault(),
                getString(R.string.pressure),
                forecast.getPressure()));
        humidity.setText(String.format(Locale.getDefault(),
                getString(R.string.humidity),
                forecast.getHumidity()));
        wind.setText(String.format(Locale.getDefault(), getString(R.string.wind),
                forecast.getWindSpeed(),
                forecast.getWindDirection()));
        weatherAdapter.setWeatherList(forecast.getWeathersList());
    }
}
