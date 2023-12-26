package ru.easycode.zerotoheroandroidtdd.task29.folder.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderDetailsBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class FolderDetailFragment : Fragment() {

    private var binding: FragmentFolderDetailsBinding? = null
    private lateinit var viewModel: FolderDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFolderDetailsBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(FolderDetailsViewModel::class.java)
        val adapter = NoteAdapter(viewModel)

        binding!!.apply {

            notesRecyclerView.adapter = adapter

            editFolderButton.setOnClickListener {
                viewModel.editFolder()
            }

            addNoteButton.setOnClickListener {
                viewModel.createNote()
            }
        }

        viewModel.folderLiveData().observe(viewLifecycleOwner) {
            it.show(binding!!)
        }

        viewModel.noteListLiveData().observe(viewLifecycleOwner){
            adapter.update(it)
        }


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    viewModel.comeback()
                }
            })
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}