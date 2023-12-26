package ru.easycode.zerotoheroandroidtdd.task29.core

import android.os.Bundle

interface BundleWrapper {

    fun save(id: Long, text: String): Bundle

    fun restoreId(): Long

    fun restoreText(): String

    class Base(private val bundle: Bundle = Bundle()) : BundleWrapper {
        override fun save(id: Long, text: String) = bundle.apply {
            putLong(ID_KEY, id)
            putString(TEXT_KEY, text)
        }


        override fun restoreId() = bundle.getLong(ID_KEY)

        override fun restoreText() = bundle.getString(TEXT_KEY) ?: ""

        companion object {
            private const val ID_KEY = "ID_KEY"
            private const val TEXT_KEY = "TEXT_KEY"
        }
    }
}