package ru.easycode.zerotoheroandroidtdd.task24

import android.os.Bundle

interface BundleWrapper {

    interface Save {
        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {
        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle,
        private val key: String = "charSequence_key",
    ) : Mutable {

        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(key, list)
        }

        override fun restore(): List<CharSequence> {
            return bundle.getCharSequenceArrayList(key) ?: emptyList()
        }
    }
}