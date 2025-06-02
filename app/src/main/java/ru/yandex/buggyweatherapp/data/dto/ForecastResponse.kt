package ru.yandex.buggyweatherapp.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastResponse(
    @SerializedName("city") val city: City,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("cod") val cod: String,
    @SerializedName("list") val list: List<Forecast>,
    @SerializedName("message") val message: Int,
) : Response(), Parcelable