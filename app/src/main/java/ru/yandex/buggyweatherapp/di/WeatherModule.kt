package ru.yandex.buggyweatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.yandex.buggyweatherapp.domain.api.WeatherInteractor
import ru.yandex.buggyweatherapp.domain.interactors.WeatherInteractorImpl

@Module
@InstallIn(ViewModelComponent::class)
interface WeatherModule {
    @Binds
    fun bindWeatherInteractor(weatherInteractor: WeatherInteractorImpl): WeatherInteractor
}