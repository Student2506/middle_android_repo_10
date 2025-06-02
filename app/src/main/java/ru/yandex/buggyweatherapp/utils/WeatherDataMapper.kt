package ru.yandex.buggyweatherapp.utils

import ru.yandex.buggyweatherapp.data.dto.CityWeatherResponse
import ru.yandex.buggyweatherapp.data.dto.LocationWeatherResponse
import ru.yandex.buggyweatherapp.domain.model.WeatherData

object WeatherDataMapper {
    fun getWeatherData(data: LocationWeatherResponse): WeatherData {

        return with(data) {
            WeatherData(
                cityName = name,
                country = sys.country,
                temperature = main.temp,
                feelsLike = main.feelsLike,
                minTemp = main.tempMin,
                maxTemp = main.tempMax,
                humidity = main.humidity,
                pressure = main.pressure,
                windSpeed = wind?.speed ?: 0.0,
                windDirection = wind?.deg ?: 0,
                description = weather[0].description,
                icon = weather[0].icon,
                cloudiness = clouds.all,
                sunriseTime = sys.sunrise.toLong(),
                sunsetTime = sys.sunset.toLong(),
                timezone = timezone,
                timestamp = dt.toLong(),
                rawApiData = data.toString(),
                rain = data.rain?.h1,
                snow = data.snow?.h1,
            )
        }
    }

    fun getWeatherDataForCity(data: CityWeatherResponse): WeatherData {

        return with(data) {
            WeatherData(
                cityName = name,
                country = sys.country,
                temperature = main.temp,
                feelsLike = main.feelsLike,
                minTemp = main.tempMin,
                maxTemp = main.tempMax,
                humidity = main.humidity,
                pressure = main.pressure,
                windSpeed = wind?.speed ?: 0.0,
                windDirection = wind?.deg ?: 0,
                description = weather[0].description,
                icon = weather[0].icon,
                cloudiness = clouds.all,
                sunriseTime = sys.sunrise.toLong(),
                sunsetTime = sys.sunset.toLong(),
                timezone = timezone,
                timestamp = dt.toLong(),
                rawApiData = data.toString(),
                rain = data.rain?.h1,
                snow = data.snow?.h1,
            )
        }
    }
}