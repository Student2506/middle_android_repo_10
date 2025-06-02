package ru.yandex.buggyweatherapp.data

import ru.yandex.buggyweatherapp.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}