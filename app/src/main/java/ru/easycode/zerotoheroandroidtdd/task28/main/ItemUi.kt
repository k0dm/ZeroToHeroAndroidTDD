package ru.easycode.zerotoheroandroidtdd.task28.main

import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.task28.details.DetailsViewModel
import java.io.Serializable

data class ItemUi(private val id: Long, private val text: String) : Serializable {
    fun areItemsSame(item: ItemUi) = item.id == id

    fun show(textView: TextView) {
        textView.text = text
    }

    fun updateItem(viewModel: DetailsViewModel, newText: String) = viewModel.update(id, newText)

    fun deleteItem(viewModel: DetailsViewModel) = viewModel.delete(id)

    fun init(viewModel: DetailsViewModel) = viewModel.init(id)
}