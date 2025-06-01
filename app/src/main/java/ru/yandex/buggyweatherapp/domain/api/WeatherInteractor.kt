package ru.yandex.buggyweatherapp.domain.api

import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.WeatherData

interface WeatherInteractor {
    fun getWeatherData(location: Location, callback: (WeatherData?, Exception?) -> Unit)

    fun getWeatherByCity(cityName: String, callback: (WeatherData?, Exception?) -> Unit)
}