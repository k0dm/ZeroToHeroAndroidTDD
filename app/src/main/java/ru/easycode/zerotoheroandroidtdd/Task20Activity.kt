package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityMainBinding
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask20Binding

class Task20Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask20Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask20Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            actionButton.setOnClickListener {
                titleTextView.text = inputEditText.text
                inputEditText.setText("")
            }
        }
    }
}