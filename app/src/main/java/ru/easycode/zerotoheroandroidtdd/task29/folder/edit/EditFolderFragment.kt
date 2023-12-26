package ru.easycode.zerotoheroandroidtdd.task29.folder.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentEditFolderBinding
import ru.easycode.zerotoheroandroidtdd.task29.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task29.core.ProvideViewModel

class EditFolderFragment : Fragment() {

    private var binding: FragmentEditFolderBinding? = null
    private lateinit var viewModel: EditFolderViewModel

    companion object {
        fun bundle(folderId: Long, folderName: String) =
            BundleWrapper.Base(Bundle()).save(folderId, folderName)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditFolderBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundleWrapper = BundleWrapper.Base(requireArguments())
        val folderId = bundleWrapper.restoreId()
        val folderName = bundleWrapper.restoreText()

        viewModel =
            (requireActivity() as ProvideViewModel).viewModel(EditFolderViewModel::class.java)

        binding!!.apply {

            saveFolderButton.isEnabled = false

            folderEditText.setText(folderName)

            folderEditText.doAfterTextChanged {
                saveFolderButton.isEnabled = it.toString().isNotEmpty()
            }

            saveFolderButton.setOnClickListener {
                viewModel.renameFolder(folderId, folderEditText.text.toString())
            }

            deleteFolderButton.setOnClickListener {
                viewModel.deleteFolder(folderId)
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