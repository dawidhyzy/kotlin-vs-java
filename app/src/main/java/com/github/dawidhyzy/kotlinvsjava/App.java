package com.github.dawidhyzy.kotlinvsjava;

import android.app.Application;
import android.content.Context;

import com.github.dawidhyzy.kotlinvsjava.jv.api.OpenWeatherMapApi;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import timber.log.Timber;

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 30/01/16.
 */
public class App extends Application {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static OpenWeatherMapApi getApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(OpenWeatherMapApi.class);
    }
}
