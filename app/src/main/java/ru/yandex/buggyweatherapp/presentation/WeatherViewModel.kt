package ru.yandex.buggyweatherapp.presentation

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.yandex.buggyweatherapp.domain.api.LocationInteractor
import ru.yandex.buggyweatherapp.domain.api.WeatherInteractor
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.Resource
import ru.yandex.buggyweatherapp.domain.model.WeatherData
import ru.yandex.buggyweatherapp.utils.ImageLoader
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherInteractor: WeatherInteractor,
    private val locationInteractor: LocationInteractor,
) : ViewModel() {
//
//
//    private lateinit var activityContext: Context
//
//
////    private val weatherRepository = WeatherRepository()
//    private val locationRepository by lazy {
//        LocationRepository(activityContext)
//    }


    val weatherData = MutableLiveData<WeatherData>()
    val currentLocation = MutableLiveData<Location>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String?>()
    val cityName = MutableLiveData<String>()


    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())


    private var refreshTimer: Timer? = null


    fun initialize(context: Context) {
//        this.activityContext = context
        fetchCurrentLocationWeather()


        startAutoRefresh()
    }


    fun fetchCurrentLocationWeather() {
        isLoading.value = true
        error.value = null

        locationInteractor.getCurrentLocation { location ->
            if (location != null) {
                currentLocation.value = location


                val cityNameFromLocation = locationInteractor.getCityNameFromLocation(location)
                cityName.value = cityNameFromLocation

                getWeatherForLocation(location)
            } else {
                isLoading.value = false
                error.value = "Unable to get current location"
            }
        }
    }

    fun getWeatherForLocation(location: Location) {
        isLoading.postValue(true)
        error.postValue(null)

        viewModelScope.launch {
            isLoading.postValue(false)
            weatherInteractor.getWeatherData(location).collect {
                if (it is Resource.Success) {
                    weatherData.postValue(it.data!!)
                } else {
                    error.postValue(it.message ?: "Unknown error")
                }
            }
        }
    }

    fun searchWeatherByCity(city: String) {
        if (city.isBlank()) {
            error.value = "City name cannot be empty"
            return
        }

        isLoading.postValue(true)
        error.postValue(null)
        viewModelScope.launch {
            weatherInteractor.getWeatherByCity(city).collect {
                isLoading.postValue(false)
                if (it is Resource.Success) {
                    weatherData.postValue(it.data!!)
                    cityName.postValue(it.data.cityName)
                    currentLocation.postValue(Location(0.0, 0.0, it.data.cityName))
                } else {
                    error.postValue(it.message ?: "Unknown error")
                }
            }
        }
    }


    fun formatTemperature(temp: Double): String {
        return "${temp.toInt()}°C"
    }

    fun loadWeatherIcon(iconCode: String) {
        coroutineScope.launch {
            val iconUrl = "https://openweathermap.org/img/wn/$iconCode@2x.png"
            ImageLoader.loadImage(iconUrl)
        }
    }


    private fun startAutoRefresh() {
        refreshTimer = Timer()
        refreshTimer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                currentLocation.value?.let { location ->
                    getWeatherForLocation(location)
                }
            }
        }, 60000, 60000)
    }

    fun toggleFavorite() {
        weatherData.value?.let {
            it.isFavorite = !it.isFavorite

            weatherData.value = it
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    companion object {
        private const val TAG = "WeatherViewModel"
    }
}