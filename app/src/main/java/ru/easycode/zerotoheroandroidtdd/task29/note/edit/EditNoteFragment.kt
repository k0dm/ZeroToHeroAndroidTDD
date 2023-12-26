package ru.easycode.zerotoheroandroidtdd.task29.note.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditNoteBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class EditNoteFragment : Fragment() {

    private var binding: FragmentEditNoteBinding? = null
    private lateinit var viewModel: EditNoteViewModel

    companion object {
        fun bundle(noteId: Long) = BundleWrapper.Base().save(noteId, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditNoteBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = BundleWrapper.Base(requireArguments()).restoreId()
        viewModel = (requireActivity() as ProvideViewModel).viewModel(EditNoteViewModel::class.java)

        binding!!.apply {

            saveNoteButton.setOnClickListener {
                viewModel.renameNote(noteId, noteEditText.text.toString())
            }

            deleteNoteButton.setOnClickListener {
                viewModel.deleteNote(noteId)
            }

            viewModel.noteTextLiveData().observe(viewLifecycleOwner) {
                noteEditText.setText(it)
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.comeback()
                }
            })
        viewModel.init(noteId)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}