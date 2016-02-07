package com.github.dawidhyzy.kotlinvsjava.jv.forecast;

import com.github.dawidhyzy.kotlinvsjava.jv.api.OpenWeatherMapApi;
import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Response;
import com.github.dawidhyzy.kotlinvsjava.jv.domain.Forecast;
import com.github.dawidhyzy.kotlinvsjava.jv.util.SimpleObserver;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 31/01/16.
 */
public class ForecastPresenter implements ForecastContract.Presenter {

    private ForecastContract.View view;
    private OpenWeatherMapApi api;
    private String city = "Kraków";

    public ForecastPresenter(ForecastContract.View view, OpenWeatherMapApi api) {
        this.view = view;
        this.api = api;

    }

    @Override public void loadForecast() {
        api.getWeatherByCityName(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override public void call() {
                        view.showLoading(true);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override public void call() {
                        view.showLoading(false);
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        view.showLoading(false);
                    }
                })
                .map(new Func1<Response, Forecast>() {
                    @Override public Forecast call(Response response) {
                        return new Forecast(city, response);
                    }
                })
                .subscribe(new SimpleObserver<Forecast>(){
                    @Override public void onNext(Forecast forecast) {
                        view.setForecast(forecast);
                    }

                    @Override public void onError(Throwable e) {
                        e.printStackTrace();
                        view.showError(e.getMessage());
                    }
                });
    }

    @Override public void refresh() {
        loadForecast();
    }
}
