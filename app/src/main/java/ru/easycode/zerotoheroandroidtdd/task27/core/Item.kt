package ru.easycode.zerotoheroandroidtdd.task27.core

import ru.easycode.zerotoheroandroidtdd.task27.main.ItemUi

data class Item(private val id: Long, private val text: String) {

    fun toUi() = ItemUi(id, text)
}