package ru.yandex.buggyweatherapp.domain.interactors

import ru.yandex.buggyweatherapp.domain.api.WeatherInteractor
import ru.yandex.buggyweatherapp.domain.api.WeatherRepositoryApi
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.WeatherData
import javax.inject.Inject

class WeatherInteractorImpl @Inject constructor(
    private val repository: WeatherRepositoryApi,
) : WeatherInteractor {
    override fun getWeatherData(
        location: Location,
        callback: (WeatherData?, Exception?) -> Unit,
    ) {
        return repository.getWeatherData(location, callback)
    }

    override fun getWeatherByCity(
        cityName: String,
        callback: (WeatherData?, Exception?) -> Unit,
    ) {
        return repository.getWeatherByCity(cityName, callback)
    }
}