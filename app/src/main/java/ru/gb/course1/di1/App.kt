package ru.gb.course1.di1

import android.app.Application
import android.content.Context
import ru.gb.course1.di1.di.DaggerMyComponent
import ru.gb.course1.di1.di.MyModule

class App : Application() {
    val di by lazy {
        DaggerMyComponent.builder()
            .myModule(MyModule(this))
            .build()
    }

}

val Context.app: App
    get() = applicationContext as App