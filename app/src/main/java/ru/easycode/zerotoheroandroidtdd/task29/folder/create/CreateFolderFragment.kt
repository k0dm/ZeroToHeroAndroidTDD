package ru.easycode.zerotoheroandroidtdd.task29.folder.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateFolderBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class CreateFolderFragment : Fragment() {

    private var binding: FragmentCreateFolderBinding? = null
    private lateinit var viewModel: CreateFolderViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateFolderBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(CreateFolderViewModel::class.java)

        binding!!.apply {
            saveFolderButton.isEnabled = false
            createFolderEditText.doAfterTextChanged {
                saveFolderButton.isEnabled = it.toString().length >= 3
            }

            saveFolderButton.setOnClickListener {
                viewModel.createFolder(createFolderEditText.text.toString())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.comeback()
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}