package ru.easycode.zerotoheroandroidtdd.task28.core

interface Now {
    fun nowMillis(): Long

    class Base : Now {
        override fun nowMillis() = System.currentTimeMillis()
    }
}