package ru.yandex.buggyweatherapp.domain.interactors

import kotlinx.coroutines.flow.Flow
import ru.yandex.buggyweatherapp.domain.api.WeatherInteractor
import ru.yandex.buggyweatherapp.domain.api.WeatherRepositoryApi
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.Resource
import ru.yandex.buggyweatherapp.domain.model.WeatherData
import javax.inject.Inject

class WeatherInteractorImpl @Inject constructor(
    private val repository: WeatherRepositoryApi,
) : WeatherInteractor {
    override fun getWeatherData(location: Location): Flow<Resource<WeatherData>> {
        return repository.getWeatherData(location)
    }

    override fun getWeatherByCity(cityName: String): Flow<Resource<WeatherData>> {
        return repository.getWeatherByCity(cityName)
    }
}