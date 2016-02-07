package com.github.dawidhyzy.kotlinvsjava

import android.app.Application
import com.github.dawidhyzy.kotlinvsjava.kt.api.OpenWeatherMapApi
import com.github.dawidhyzy.kotlinvsjava.kt.extensions.DelegatesExt
import timber.log.Timber

/**
 * @author Dawid Hyży <dawid.hyzy@seedlabs.io>
 * @since 06/02/16.
 */
class KotlinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this;
        api = OpenWeatherMapApi.create(instance)
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree());
    }

    companion object{
        var instance: KotlinApp by DelegatesExt.notNullSingleValue()
        var api: OpenWeatherMapApi by DelegatesExt.notNullSingleValue();
    }


}

