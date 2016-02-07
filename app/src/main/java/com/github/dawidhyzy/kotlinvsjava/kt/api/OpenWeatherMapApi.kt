package com.github.dawidhyzy.kotlinvsjava.kt.api

import android.content.Context
import com.github.dawidhyzy.kotlinvsjava.R
import com.github.dawidhyzy.kotlinvsjava.jv.api.model.Response
import okhttp3.OkHttpClient
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.RxJavaCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import timber.log.Timber

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 05/02/16.
 */
interface OpenWeatherMapApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") city: String): Observable<Response>

    companion object{
        fun create(context: Context): OpenWeatherMapApi {

            val builder = OkHttpClient.Builder()
            builder.addInterceptor { chain ->
                val url = chain.request().url().newBuilder()
                        .addQueryParameter("APPID", context.getString(R.string.app_id)).build()

                val request = chain.request().newBuilder().url(url).build()

                val originalResponse = chain.proceed(request)
                Timber.d("Url: %s", url.url().toString())

                originalResponse.newBuilder().build()
            }

            val retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.base_url))
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

            return retrofit.create(OpenWeatherMapApi::class.java)

        }
    }
}