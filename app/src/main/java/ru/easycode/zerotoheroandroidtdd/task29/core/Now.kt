package ru.easycode.zerotoheroandroidtdd.task29.core

interface Now {

    fun timeInMillis(): Long

    class Base : Now {
        override fun timeInMillis() = System.currentTimeMillis()
    }
}