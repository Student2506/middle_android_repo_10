package ru.yandex.buggyweatherapp.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Forecast(
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("dt_txt") val dtTxt: String,
    @SerializedName("main") val main: Main,
    @SerializedName("pop") val pop: Double,
    @SerializedName("rain") val rain: Rain?,
    @SerializedName("sys") val sys: Pod,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind,
) : Parcelable