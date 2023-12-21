package ru.easycode.zerotoheroandroidtdd.task27.main

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.task27.delete.DeleteViewModel
import java.io.Serializable

data class ItemUi(private val id: Long, private val text: String): Serializable {

    fun areItemsTheSame(itemUi: ItemUi)= itemUi.id == this.id

    fun show(textView: TextView) {
        textView.text = text
    }

    fun delete(deleteViewModel: DeleteViewModel) = deleteViewModel.delete(id)
}