package ru.yandex.buggyweatherapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import ru.yandex.buggyweatherapp.di.ApplicationComponent
import ru.yandex.buggyweatherapp.di.DaggerApplicationComponent
import ru.yandex.buggyweatherapp.utils.ImageLoader
import ru.yandex.buggyweatherapp.utils.LocationTracker

@HiltAndroidApp
class WeatherApplication : Application() {
    lateinit var appComponent: ApplicationComponent
    
    companion object {
        lateinit var appContext: Context
            private set
    }
    
    override fun onCreate() {
        super.onCreate()
        
        
        appContext = this
        
        
        ImageLoader.initialize(this)
        LocationTracker.getInstance(this)
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}