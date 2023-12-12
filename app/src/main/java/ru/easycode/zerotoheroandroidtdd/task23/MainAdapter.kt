package ru.easycode.zerotoheroandroidtdd.task23

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.ItemListBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val textList: MutableList<String> = mutableListOf()

    fun add(text: String) {
        textList.add(text)
        notifyItemInserted(textList.lastIndex)
    }


    fun restore(bundle: Bundle) {
        val res = bundle.getStringArray(KEY)
        if (res != null) {
            textList.addAll(res)
            textList.addAll(textList)
            notifyDataSetChanged()
        }
    }

    fun save(bundle: Bundle) {
        bundle.putStringArray(KEY, textList.toTypedArray())
    }

    companion object {
        private const val KEY = "TEXT_LIST_KEY"
    }


    class MainViewHolder(
        private val itemList: ItemListBinding,
    ) : RecyclerView.ViewHolder(itemList.root) {
        init {
            Log.d("k0dm", "MainViewHolder create")
        }

        fun bind(text: String) {
            itemList.elementTextView.text = text
        }
    }

    private var onCreateViewHolder = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        Log.d("k0dm", "onCreateViewHolder ${++onCreateViewHolder}")
        val textView = ItemListBinding.inflate(LayoutInflater.from(parent.context))
        return MainViewHolder(textView)
    }

    override fun getItemCount(): Int = textList.size

    private var onBindViewHolderCount = 0

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Log.d("k0dm", "onBindViewHolder ${++onBindViewHolderCount}")
        holder.bind(textList[position])
    }
}