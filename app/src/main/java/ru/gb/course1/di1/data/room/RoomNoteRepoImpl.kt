package ru.gb.course1.di1.data.room

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteRepo

@SuppressLint("StaticFieldLeak")
class RoomNoteRepoImpl(private val noteDao: NoteDao) : NoteRepo {
    private val liveData = MutableLiveData<List<NoteEntity>>()

    override fun getNotes(): List<NoteEntity> {
        return noteDao.getNotes()
    }

    override fun getNotesLiveData(): LiveData<List<NoteEntity>> {
        return liveData
    }

    override fun clear() {
        noteDao.clear()
        liveData.postValue(getNotes())
    }

    override fun put(note: NoteEntity) {
        noteDao.put(note)
        liveData.postValue(getNotes())
    }
}