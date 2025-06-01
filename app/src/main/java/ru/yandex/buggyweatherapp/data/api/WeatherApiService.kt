package ru.yandex.buggyweatherapp.data.api

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yandex.buggyweatherapp.data.dto.CityWeatherResponse
import ru.yandex.buggyweatherapp.data.dto.ForecastResponse
import ru.yandex.buggyweatherapp.data.dto.LocationWeatherResponse

interface WeatherApiService {


    companion object {
        const val API_KEY = "8fd9a0f2216e2bc16a09102e2af8ab1d"
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }
//    [55.75308338761605,37.58767489392091]

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric",
    ): LocationWeatherResponse

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric",
    ): CityWeatherResponse

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric",
    ): ForecastResponse
}