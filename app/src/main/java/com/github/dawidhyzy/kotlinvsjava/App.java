package com.github.dawidhyzy.kotlinvsjava;

import android.app.Application;
import android.content.Context;

import com.github.dawidhyzy.kotlinvsjava.jv.api.OpenWeatherMapApi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override public Response intercept(Chain chain) throws IOException {

                HttpUrl url = chain.request().url().newBuilder()
                        .addQueryParameter("APPID", context.getString(R.string.app_id)).build();

                Request request = chain.request().newBuilder().url(url).build();

                Response originalResponse = chain.proceed(request);
                Timber.d("Url: %s", url.url().toString());

                return originalResponse.newBuilder().build();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(OpenWeatherMapApi.class);
    }
}
