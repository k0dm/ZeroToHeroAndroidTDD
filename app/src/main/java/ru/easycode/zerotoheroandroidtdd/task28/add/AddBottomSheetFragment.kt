package ru.easycode.zerotoheroandroidtdd.task28.add

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.R
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentAddBottomSheetBinding
import ru.easycode.zerotoheroandroidtdd.task28.core.ProvideViewModel

class AddBottomSheetFragment : BottomSheetDialogFragment() {

    private var binding: FragmentAddBottomSheetBinding? = null
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewModel = (requireActivity() as ProvideViewModel).viewModel(AddViewModel::class.java)
        binding = FragmentAddBottomSheetBinding.inflate(inflater)
        return binding!!.root
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

        binding!!.apply {

            saveButton.setOnClickListener {
                viewModel.add(addInputEditText.text.toString())
                dismiss()
            }
        }

        (dialog as BottomSheetDialog)
            .findViewById<View>(com.google.android.material.R.id.touch_outside)!!
            .setOnClickListener {
                viewModel.comeback()
                dismiss()
            }
    }
}