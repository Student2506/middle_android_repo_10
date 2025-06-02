package ru.yandex.buggyweatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.yandex.buggyweatherapp.domain.api.LocationInteractor
import ru.yandex.buggyweatherapp.domain.interactors.LocationInteractorImpl

@Module
@InstallIn(ViewModelComponent::class)
interface LocationModule {
    @Binds
    fun bindLocationInteractor(locationInteractor: LocationInteractorImpl): LocationInteractor
}