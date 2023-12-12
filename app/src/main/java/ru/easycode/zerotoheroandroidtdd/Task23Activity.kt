package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask23Binding
import ru.easycode.zerotoheroandroidtdd.task23.MainAdapter

class Task23Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask23Binding
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask23Binding.inflate(layoutInflater)
        setContentView(binding.rootLayout)

        adapter = MainAdapter()

        binding.apply {

            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@Task23Activity)

            actionButton.setOnClickListener {
                val inputText = inputEditText.text.toString()
                inputEditText.setText("")
                adapter.add(inputText)
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        adapter.restore(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        adapter.save(outState)
    }

}