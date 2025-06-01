package ru.yandex.buggyweatherapp.domain.interactors

import ru.yandex.buggyweatherapp.domain.api.LocationInteractor
import ru.yandex.buggyweatherapp.domain.api.LocationRepositoryApi
import ru.yandex.buggyweatherapp.domain.model.Location
import javax.inject.Inject

class LocationInteractorImpl @Inject constructor(
    private val repository: LocationRepositoryApi,
) : LocationInteractor {
    override fun getCurrentLocation(callback: (Location?) -> Unit) {
        return repository.getCurrentLocation(callback)
    }

    override fun getCityNameFromLocation(location: Location): String? {
        return repository.getCityNameFromLocation(location)
    }

    override fun startLocationTracking() {
        return repository.startLocationTracking()
    }
}