package com.github.dawidhyzy.kotlinvsjava.jv.forecast;

import com.github.dawidhyzy.kotlinvsjava.jv.api.OpenWeatherMapApi;
import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Response;
import com.github.dawidhyzy.kotlinvsjava.jv.domain.Forecast;
import com.github.dawidhyzy.kotlinvsjava.jv.util.SimpleObserver;

import java.lang.ref.WeakReference;

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

    private WeakReference<ForecastContract.View> view;
    private OpenWeatherMapApi api;
    private String city = "Kraków";

    public ForecastPresenter(ForecastContract.View view, OpenWeatherMapApi api) {
        this.view = new WeakReference<>(view);
        this.api = api;

    }

    @Override public void loadForecast() {
        api.getWeatherByCityName(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        if(view.get() != null) {
                            view.get().showLoading(true);
                        }
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        if(view.get() != null) {
                            view.get().showLoading(false);
                        }
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        if(view.get() != null) {
                            view.get().showLoading(false);
                        }
                    }
                })
                .map(new Func1<Response, Forecast>() {
                    @Override
                    public Forecast call(Response response) {
                        return new Forecast(city, response);
                    }
                })
                .subscribe(new SimpleObserver<Forecast>(){

                    @Override
                    public void onNext(Forecast forecast) {
                        if(view.get() != null) {
                            view.get().setForecast(forecast);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        e.printStackTrace();
                        if(view.get() != null) {
                            view.get().showError(e.getMessage());
                        }
                    }
                });
    }

    @Override public void refresh() {
        loadForecast();
    }
}
