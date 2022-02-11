package ru.gb.course1.di1.di

import android.content.Context
import androidx.room.Room
import ru.gb.course1.di1.data.RandomNoteFactoryImpl
import ru.gb.course1.di1.data.room.NoteDb
import ru.gb.course1.di1.data.room.RoomNoteRepoImpl
import ru.gb.course1.di1.domain.NoteFactory
import ru.gb.course1.di1.domain.NoteRepo
import ru.gb.course1.di1.ui.Presenter
import ru.gb.course1.di1.ui.Router
import java.util.*

fun dependencies(context: Context) {
    single<Context> { context }

    single("dbname") { "notes" }
    factory("uuid") { UUID.randomUUID().toString() }

    factory { Presenter(get("uuid"), Random().nextInt()) }
    single { Router() }
    single {
        Room.databaseBuilder(
            get(),
            NoteDb::class.java,
            get("dbname")
        ).build()
    }
    single<NoteFactory> { RandomNoteFactoryImpl() }
    single { get<NoteDb>().noteDao() }
    single<NoteRepo> { RoomNoteRepoImpl(get()) }
}