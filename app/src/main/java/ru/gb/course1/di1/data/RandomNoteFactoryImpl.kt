package ru.gb.course1.di1.data

import android.content.Context
import ru.gb.course1.di1.app
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteFactory

class RandomNoteFactoryImpl(val context: Context) : NoteFactory {
    private var counter = 0

    override fun createNewNote(): NoteEntity {
        return NoteEntity(context.app.di.getUuid(), "Заголовок ${counter++}", "Текст заметки")
    }
}