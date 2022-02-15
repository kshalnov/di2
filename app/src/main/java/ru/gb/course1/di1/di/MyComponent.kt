package ru.gb.course1.di1.di

import dagger.Component
import ru.gb.course1.di1.ui.MainActivity
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(modules = [MyModule::class])
interface MyComponent {

    fun inject(mainActivity: MainActivity)

    @Named("uuid")
    fun getUuid(): String
}