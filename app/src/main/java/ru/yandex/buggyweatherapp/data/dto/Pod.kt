package ru.yandex.buggyweatherapp.data.dto


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Pod(
    @SerializedName("pod")
    val pod: String
) : Parcelable