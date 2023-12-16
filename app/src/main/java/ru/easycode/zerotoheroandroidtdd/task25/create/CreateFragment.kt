package ru.easycode.zerotoheroandroidtdd.task25.create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateBinding
import ru.easycode.zerotoheroandroidtdd.task25.core.App
import ru.easycode.zerotoheroandroidtdd.task25.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task25.core.ProvideViewModel


class CreateFragment : Fragment() {


    private var binding: FragmentCreateBinding? = null
    private lateinit var viewModel: CreateViewModel

    private val clasz = CreateViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("k0dm", "onCreate CreateFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("k0dm", "onCreateView CreateFragment")
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_create, container, false)
        binding = FragmentCreateBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("k0dm", "onViewCreated CreateFragment")

        viewModel = (requireActivity() as ProvideViewModel).viewModel(clasz)

        binding!!.apply {
            inputEditText.doAfterTextChanged {
                createButton.isEnabled = !it.isNullOrBlank() && it.toString().length > 2
            }

            createButton.setOnClickListener {
                viewModel.add(inputEditText.text.toString())
                it.isEnabled = false
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("k0dm", "handleOnBackPressed CreateFragment")
                    viewModel.comeback()
                }
            }
        )
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("k0dm", "onViewStateRestored CreateFragment")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("k0dm", "onSaveInstanceState CreateFragment")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("k0dm", "onDestroyView CreateFragment")
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("k0dm", "onDestroy CreateFragment")
    }
}