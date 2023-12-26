package ru.easycode.zerotoheroandroidtdd.task29.note.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentCreateNoteBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class CreateNoteFragment : Fragment() {

    private var binding: FragmentCreateNoteBinding? = null
    private lateinit var viewModel: CreateNoteViewModel

    companion object {
        fun bundle(folderId: Long) = BundleWrapper.Base().save(folderId, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateNoteBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val folderId = BundleWrapper.Base(requireArguments()).restoreId()
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(CreateNoteViewModel::class.java)

        binding!!.apply {
            saveNoteButton.setOnClickListener {
                viewModel.createNote(folderId, createNoteEditText.text.toString())
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
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