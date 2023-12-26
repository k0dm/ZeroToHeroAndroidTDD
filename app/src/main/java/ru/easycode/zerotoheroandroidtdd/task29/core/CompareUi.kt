package ru.easycode.zerotoheroandroidtdd.task29.core

interface CompareUi<T> {

    fun areItemsTheSame(uiItem: T): Boolean
}