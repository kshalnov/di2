package ru.gb.course1.di1

import android.app.Application
import android.content.Context
import android.util.Log
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import ru.gb.course1.di1.di.AnnotationModule
import ru.gb.course1.di1.di.appModule
import ru.gb.course1.di1.di.dbModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, dbModule, AnnotationModule().module)
        }
    }

}