package ru.gb.course1.di1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.gb.course1.di1.data.room.NoteDao
import ru.gb.course1.di1.data.room.NoteDb
import ru.gb.course1.di1.data.room.RoomNoteRepoImpl
import ru.gb.course1.di1.domain.NoteRepo
import javax.inject.Named
import javax.inject.Singleton

@Module
class DbModule {
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

}