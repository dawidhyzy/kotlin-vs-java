package com.github.dawidhyzy.kotlinvsjava.jv.api;

import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 30/01/16.
 */
public interface OpenWeatherMapApi {

    @GET("weather") Observable<Response> getWeatherByCityName(@Query("q") String city);
}
