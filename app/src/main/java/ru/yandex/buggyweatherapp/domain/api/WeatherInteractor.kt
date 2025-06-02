package ru.yandex.buggyweatherapp.domain.api

import kotlinx.coroutines.flow.Flow
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.Resource
import ru.yandex.buggyweatherapp.domain.model.WeatherData

interface WeatherInteractor {
    fun getWeatherData(location: Location): Flow<Resource<WeatherData>>

    fun getWeatherByCity(cityName: String): Flow<Resource<WeatherData>>
}