package ru.yandex.buggyweatherapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yandex.buggyweatherapp.data.NetworkClient
import ru.yandex.buggyweatherapp.data.api.WeatherApiService
import ru.yandex.buggyweatherapp.data.dto.CityWeatherRequest
import ru.yandex.buggyweatherapp.data.dto.LocationWeatherRequest
import ru.yandex.buggyweatherapp.data.dto.Response

class RetrofitNetworkClient(
    private val weatherApiService: WeatherApiService,
    @ApplicationContext private val context: Context,
) : NetworkClient{

    override suspend fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }

        if (dto !is CityWeatherRequest && dto !is LocationWeatherRequest) {
            return Response().apply { resultCode = 400 }
        }

        return withContext(Dispatchers.IO) {
           try {
               when (dto) {
                   is CityWeatherRequest -> {
                       val response = weatherApiService.getWeatherByCity(dto.cityName)
                       response.apply { resultCode = 200 }
                   }
                   is LocationWeatherRequest {
                       val response = weatherApiService.getCurrentWeather(dto.location.latitude, dto.location.latitude)
                   }
               }

           }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }

        }
        return false
    }
}