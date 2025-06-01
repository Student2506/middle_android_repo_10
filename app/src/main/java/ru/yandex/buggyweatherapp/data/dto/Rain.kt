package ru.yandex.buggyweatherapp.data.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import ru.yandex.buggyweatherapp.data.dto.Response

@Parcelize
data class Rain(
    @SerializedName("3h")
    val h: Double
) : Parcelable