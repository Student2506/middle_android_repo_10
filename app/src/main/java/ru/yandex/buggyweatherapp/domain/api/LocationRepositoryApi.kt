package ru.yandex.buggyweatherapp.domain.api

import ru.yandex.buggyweatherapp.domain.model.Location


interface LocationRepositoryApi {
    fun getCurrentLocation(callback: (Location?) -> Unit)
    fun getCityNameFromLocation(location: Location): String?
    fun startLocationTracking()
}