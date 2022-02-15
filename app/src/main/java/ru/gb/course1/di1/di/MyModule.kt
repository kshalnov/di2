package ru.gb.course1.di1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.gb.course1.di1.data.RandomNoteFactoryImpl
import ru.gb.course1.di1.data.room.NoteDao
import ru.gb.course1.di1.data.room.NoteDb
import ru.gb.course1.di1.data.room.RoomNoteRepoImpl
import ru.gb.course1.di1.domain.NoteFactory
import ru.gb.course1.di1.domain.NoteRepo
import ru.gb.course1.di1.ui.Presenter
import ru.gb.course1.di1.ui.Router
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class MyModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Singleton
    @Named("dbname")
    @Provides
    fun provideDbName(): String = "dbname-sdfvs-dv-sdv-sdv--sdv-d"

    @Singleton
    @Provides
    fun provideNoteDb(@Named("dbname") dbname: String, context: Context): NoteDb =
        Room.databaseBuilder(
            context,
            NoteDb::class.java,
            dbname
        ).build()

    @Singleton
    @Provides
    fun provideNoteDao(noteDb: NoteDb): NoteDao = noteDb.noteDao()

    @Singleton
    @Provides
    fun provideNoteRepo(noteDao: NoteDao): NoteRepo = RoomNoteRepoImpl(noteDao)

    @Singleton
    @Provides
    fun provideNoteFactory(context: Context): NoteFactory = RandomNoteFactoryImpl(context)

    @Singleton
    @Provides
    fun provideRouter(): Router = Router()

    @Named("uuid")
    @Provides
    fun provideUuid(): String = UUID.randomUUID().toString()

    @Provides
    fun providePresenter(@Named("uuid") uuid: String): Presenter {
        return Presenter(uuid, Random().nextInt())
    }
}