package ru.gb.course1.di1.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.gb.course1.di1.data.RandomNoteFactoryImpl
import ru.gb.course1.di1.domain.NoteFactory
import ru.gb.course1.di1.ui.Router
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class MyModule {

    @Singleton
    @Provides
    fun provideNoteFactory(context: Context): NoteFactory = RandomNoteFactoryImpl(context)

    @Singleton
    @Provides
    fun provideRouter(): Router = Router()

    @Named("uuid")
    @Provides
    fun provideUuid(): String = UUID.randomUUID().toString()

}