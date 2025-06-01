package ru.yandex.buggyweatherapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component


@Component(modules = [CoreModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ) : ApplicationComponent
    }
}