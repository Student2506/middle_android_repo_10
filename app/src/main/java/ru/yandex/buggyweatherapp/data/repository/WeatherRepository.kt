package ru.yandex.buggyweatherapp.data.repository

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.yandex.buggyweatherapp.data.NetworkClient
import ru.yandex.buggyweatherapp.data.dto.CityWeatherRequest
import ru.yandex.buggyweatherapp.data.dto.CityWeatherResponse
import ru.yandex.buggyweatherapp.data.dto.LocationWeatherRequest
import ru.yandex.buggyweatherapp.data.dto.LocationWeatherResponse
import ru.yandex.buggyweatherapp.domain.api.WeatherRepositoryApi
import ru.yandex.buggyweatherapp.domain.model.Location
import ru.yandex.buggyweatherapp.domain.model.Resource
import ru.yandex.buggyweatherapp.domain.model.WeatherData
import ru.yandex.buggyweatherapp.utils.WeatherDataMapper
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val networkClient: NetworkClient,
    @ApplicationContext private val context: Context,
) : WeatherRepositoryApi {

    private var cachedWeatherData: WeatherData? = null

    override fun getWeatherData(location: Location): Flow<Resource<WeatherData>> = flow {

        val response = networkClient.doRequest(LocationWeatherRequest(location))
        Log.d(TAG, "It returns $response and ${response.resultCode}")
        when (response.resultCode) {
            -1 -> emit(Resource.Failure("Check network connectivity"))
            200 -> {
                cachedWeatherData =
                    WeatherDataMapper.getWeatherData(response as LocationWeatherResponse)
                Log.d(TAG, "I got $cachedWeatherData")
                emit(
                    Resource.Success(
                        cachedWeatherData!!
                    )
                )
            }

            400 -> emit(Resource.Failure("Wrong incoming data"))
            500 -> emit(Resource.Failure("Server error"))
        }
    }

    override fun getWeatherByCity(cityName: String): Flow<Resource<WeatherData>> = flow {
        val response = networkClient.doRequest(CityWeatherRequest(cityName))
        when (response.resultCode) {
            -1 -> emit(Resource.Failure("Check network connectivity"))
            200 -> {
                cachedWeatherData =
                    WeatherDataMapper.getWeatherDataForCity(response as CityWeatherResponse)
                emit(
                    Resource.Success(
                        cachedWeatherData!!
                    )
                )
            }

            400 -> emit(Resource.Failure("Wrong incoming data"))
            500 -> emit(Resource.Failure("Server error"))
        }
    }


    private companion object {
        const val TAG = "WeatherRepository"
    }

}