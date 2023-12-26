package ru.easycode.zerotoheroandroidtdd.task29.note.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideLiveData

interface NoteLiveDataWrapper {

    interface Read{
        fun noteTextLiveData(): LiveData<String>

    }

    interface ReadAndUpdate: Read{

        fun update(noteText: String)
    }


    class Base() : ReadAndUpdate{

        private val noteTextLiveData = MutableLiveData<String>()
        override fun update(noteText: String) {
            noteTextLiveData.value = noteText
        }

        override fun noteTextLiveData() = noteTextLiveData
    }
}