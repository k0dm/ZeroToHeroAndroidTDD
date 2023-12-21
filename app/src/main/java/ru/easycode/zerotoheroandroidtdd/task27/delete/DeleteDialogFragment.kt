package ru.easycode.zerotoheroandroidtdd.task27.delete

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.DeleteDialogFragmentBinding
import ru.easycode.zerotoheroandroidtdd.task27.core.ProvideViewModel
import ru.easycode.zerotoheroandroidtdd.task27.main.ItemUi
import java.security.Key

class DeleteDialogFragment() : DialogFragment() {

    private lateinit var binding: DeleteDialogFragmentBinding
    private lateinit var viewModel: DeleteViewModel
    private lateinit var itemUi: ItemUi

    companion object {

        fun newInstance(itemUi: ItemUi): DeleteDialogFragment {
            val instance = DeleteDialogFragment()
            instance.arguments = Bundle().apply {
                putSerializable(KEY, itemUi)
            }
            return instance
        }

        private const val KEY = "itemUiKEY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = (requireActivity() as ProvideViewModel).viewModel(DeleteViewModel::class.java)
        binding = DeleteDialogFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        object : Dialog(requireActivity()) {
            override fun onBackPressed() {
                viewModel.comeback()
                super.onBackPressed()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemUi = requireArguments().getSerializable(KEY) as ItemUi

        binding.apply {

            itemUi.show(itemTitleTextView)
            deleteButton.setOnClickListener {
                itemUi.delete(viewModel)
                dismiss()
            }
        }
    }

}