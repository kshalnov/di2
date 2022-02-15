package ru.gb.course1.di1

import android.app.Application
import android.content.Context
import ru.gb.course1.di1.di.AppModule
import ru.gb.course1.di1.di.DaggerAppComponent

class App : Application() {
    val di by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}

val Context.app: App
    get() = applicationContext as App