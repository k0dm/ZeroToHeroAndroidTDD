package ru.easycode.zerotoheroandroidtdd.task29.note.edit

import androidx.fragment.app.FragmentManager
import ru.easycode.zerotoheroandroidtdd.task29.main.Screen

data class EditNoteScreen(private val noteId: Long) :
    Screen.Replace(EditNoteFragment::class.java, EditNoteFragment.bundle(noteId))