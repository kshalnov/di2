package ru.gb.course1.di1.data

import ru.gb.course1.di1.di.get
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteFactory

class RandomNoteFactoryImpl : NoteFactory {
    private var counter = 0

    override fun createNewNote(): NoteEntity {
        return NoteEntity(get("uuid"), "Заголовок ${counter++}", "Текст заметки")
    }
}