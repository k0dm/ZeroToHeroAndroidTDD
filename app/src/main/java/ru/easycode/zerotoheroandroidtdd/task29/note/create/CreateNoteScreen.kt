package ru.easycode.zerotoheroandroidtdd.task29.note.create

import ru.easycode.zerotoheroandroidtdd.task29.main.Screen

data class CreateNoteScreen(private val folderId: Long) :
    Screen.Replace(CreateNoteFragment::class.java, CreateNoteFragment.bundle(folderId))