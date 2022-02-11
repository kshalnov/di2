package ru.gb.course1.di1.domain

import androidx.lifecycle.LiveData

interface NoteRepo {
    fun getNotes(): List<NoteEntity>
    fun getNotesLiveData(): LiveData<List<NoteEntity>>
    fun clear()
    fun put(note: NoteEntity)
}