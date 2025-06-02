package ru.yandex.buggyweatherapp.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rain(
    @SerializedName("3h") val h3: Double?,
    @SerializedName("1h") val h1: Double?,
) : Parcelable