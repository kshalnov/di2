package ru.gb.course1.di1.data

import android.content.Context
import org.koin.core.context.GlobalContext.get
import org.koin.core.qualifier.named
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteFactory

class RandomNoteFactoryImpl(val context: Context) : NoteFactory {
    private var counter = 0

    override fun createNewNote(): NoteEntity {
        return NoteEntity(get().get(named("uuid")), "Заголовок ${counter++}", "Текст заметки")
    }
}