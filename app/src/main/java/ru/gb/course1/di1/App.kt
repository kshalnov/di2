package ru.gb.course1.di1

import android.app.Application
import ru.gb.course1.di1.di.dependencies

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        dependencies(this)
    }

}