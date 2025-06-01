package ru.yandex.buggyweatherapp.domain.api

import kotlinx.coroutines.flow.Flow
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.Resource
import ru.yandex.buggyweatherapp.domain.model.WeatherData

interface WeatherRepositoryApi {
    fun getWeatherData(
        location: Location,
        callback: (WeatherData?, Exception?) -> Unit,
    ): Flow<Resource<WeatherData>>

    fun getWeatherByCity(cityName: String, callback: (WeatherData?, Exception?) -> Unit)
}