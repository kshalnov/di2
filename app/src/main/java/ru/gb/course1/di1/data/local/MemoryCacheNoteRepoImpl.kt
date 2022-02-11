package ru.gb.course1.di1.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.gb.course1.di1.domain.NoteEntity
import ru.gb.course1.di1.domain.NoteRepo

class MemoryCacheNoteRepoImpl : NoteRepo {
    private val liveData = MutableLiveData<List<NoteEntity>>()

    private val cache: MutableList<NoteEntity> = mutableListOf()

    override fun getNotes(): List<NoteEntity> = ArrayList(cache)

    override fun getNotesLiveData(): LiveData<List<NoteEntity>> {
        return liveData
    }

    override fun clear() {
        cache.clear()
        liveData.postValue(getNotes())
    }

    override fun put(note: NoteEntity) {
        cache.add(note)
        liveData.postValue(getNotes())
    }
}