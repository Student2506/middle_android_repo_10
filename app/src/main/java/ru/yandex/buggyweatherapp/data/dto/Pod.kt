package ru.yandex.buggyweatherapp.data.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pod(
    @SerializedName("pod") val pod: String,
) : Parcelable