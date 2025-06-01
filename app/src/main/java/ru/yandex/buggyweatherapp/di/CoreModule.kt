package ru.yandex.buggyweatherapp.di

import android.content.Context
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yandex.buggyweatherapp.data.api.WeatherApiService
import ru.yandex.buggyweatherapp.data.repository.LocationRepository
import ru.yandex.buggyweatherapp.data.repository.WeatherRepository
import ru.yandex.buggyweatherapp.domain.api.LocationRepositoryApi
import ru.yandex.buggyweatherapp.domain.api.WeatherRepositoryApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepository: WeatherRepository): WeatherRepositoryApi

    @Binds
    @Singleton
    fun bindLocationRepository(locationRepository: LocationRepository): LocationRepositoryApi

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(@ApplicationContext context: Context): WeatherApiService {
            return Retrofit.Builder().baseUrl(WeatherApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson())).build()
                .create(WeatherApiService::class.java)
        }
    }
}