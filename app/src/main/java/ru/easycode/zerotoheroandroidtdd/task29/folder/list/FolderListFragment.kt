package ru.easycode.zerotoheroandroidtdd.task29.folder.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentFolderListBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class FolderListFragment : Fragment() {

    private var binding: FragmentFolderListBinding? = null
    private lateinit var viewModel: FolderListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentFolderListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(FolderListViewModel::class.java)
        val adapter = FolderAdapter(viewModel)
        binding!!.apply {
           // foldersRecyclerView.layoutManager = GridLayoutManager(requireActivity(), 1)
            foldersRecyclerView.adapter = adapter

            addButton.setOnClickListener {
                viewModel.addFolder()
            }

        }
        viewModel.folderListLiveData().observe(viewLifecycleOwner) {
            adapter.update(it)
        }

        viewModel.init()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}