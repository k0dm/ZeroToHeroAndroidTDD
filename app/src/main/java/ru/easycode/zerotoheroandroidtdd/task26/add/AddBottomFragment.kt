package ru.easycode.zerotoheroandroidtdd.task26.add

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.easycode.zerotoheroandroidtdd.databinding.BottomFragmentAddBinding
import ru.easycode.zerotoheroandroidtdd.task26.core.ProvideViewModel

class AddBottomFragment : BottomSheetDialogFragment() {


    lateinit var binding: BottomFragmentAddBinding
    lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = BottomFragmentAddBinding.inflate(inflater)
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