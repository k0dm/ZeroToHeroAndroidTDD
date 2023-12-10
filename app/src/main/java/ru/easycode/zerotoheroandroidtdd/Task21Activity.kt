package ru.easycode.zerotoheroandroidtdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import ru.easycode.zerotoheroandroidtdd.databinding.ActivityTask21Binding

class Task21Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask21Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask21Binding.inflate(layoutInflater)
        setContentView(binding.rootLayout)

        binding.apply {

            actionButton.setOnClickListener {
                titleTextView.text = inputEditText.text
                inputEditText.setText("")
            }

            inputEditText.doAfterTextChanged {
                if (it.isNullOrEmpty()){
                    actionButton.isEnabled = false
                }else {
                    if (it.length >= 3){

                        actionButton.isEnabled = true
                    }
                }
            }
        }
    }
}