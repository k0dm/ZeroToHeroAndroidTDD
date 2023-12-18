package ru.easycode.zerotoheroandroidtdd.task26.core

interface Now {
    fun nowMillis(): Long

    class Base : Now {
        override fun nowMillis() = System.currentTimeMillis()
    }
}