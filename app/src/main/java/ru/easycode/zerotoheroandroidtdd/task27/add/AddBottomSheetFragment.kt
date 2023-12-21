package ru.easycode.zerotoheroandroidtdd.task27.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.AddBottomSheetFragmentBinding
import ru.easycode.zerotoheroandroidtdd.task27.core.ProvideViewModel

class AddBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: AddBottomSheetFragmentBinding
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = AddBottomSheetFragmentBinding.inflate(inflater)
        viewModel = (requireActivity() as ProvideViewModel).viewModel(AddViewModel::class.java)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        object : BottomSheetDialog(requireActivity(), theme) {

            override fun onBackPressed() {
                viewModel.comeback()
                super.onBackPressed()
            }
        }.apply {
            window!!.setDimAmount(0.5f)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val touchOutside =
            (dialog as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.touch_outside)
        touchOutside!!.setOnClickListener {
            viewModel.comeback()
            dismiss()
        }

        binding.saveButton.setOnClickListener {
            viewModel.add(binding.addInputEditText.text.toString())
            dismiss()
        }
    }
}