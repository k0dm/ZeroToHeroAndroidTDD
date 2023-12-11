package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.allViews
import org.w3c.dom.Text
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask22Binding
import ru.easycode.zerotoheroandroidtdd.task22.App
import ru.easycode.zerotoheroandroidtdd.task22.MainViewModel

class Task22Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask22Binding
    private lateinit var  mainViewModel: MainViewModel
    private val contentViewsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask22Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = (application as App).mainViewModel

        binding.apply {

            actionButton.setOnClickListener{
                val inputText: String = inputEditText.text.toString()
                inputEditText.setText("")
                if (inputText.isNotEmpty()) {
                    val tv = TextView(this@Task22Activity).apply {
                        text = inputText
                    }
                    contentLayout.addView(tv)
                    contentViewsList.add(inputText)
                }
            }

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainViewModel.saveContentViews(contentViewsList)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.contentLayout.removeAllViews()
        val restoredViews = mainViewModel.restoreViews().toMutableList()


        restoredViews.forEach {
            val tv = TextView(this@Task22Activity).apply {
                text = it
            }
            binding.contentLayout.addView(tv)
        }

    }
}