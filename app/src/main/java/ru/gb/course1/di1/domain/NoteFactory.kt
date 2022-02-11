package ru.gb.course1.di1.domain

interface NoteFactory {
    fun createNewNote(): NoteEntity
}