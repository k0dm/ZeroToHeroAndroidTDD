package ru.easycode.zerotoheroandroidtdd.task25.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import ru.easycode.zerotoheroandroidtdd.databinding.FragmentListBinding
import ru.easycode.zerotoheroandroidtdd.task25.core.App
import ru.easycode.zerotoheroandroidtdd.task25.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task25.core.ProvideViewModel

class ListFragment : Fragment() {


    private var binding: FragmentListBinding? = null
    private var viewModel: ListViewModel? = null
    private val clasz = ListViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("k0dm", "onCreate ListFragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        Log.d("k0dm", "onCreateView ListFragment")

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list, container, false)
        binding = FragmentListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("k0dm", "onViewCreated ListFragment")

        viewModel = (requireActivity() as ProvideViewModel).viewModel(clasz)

        val adapter = MyListAdapter()
        viewModel!!.liveData().observe(viewLifecycleOwner) { list ->
            adapter.update(ArrayList(list))
        }

        binding!!.apply {
            recyclerView.adapter = adapter

            addButton.setOnClickListener {
                viewModel!!.create()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() = Unit
        })
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("k0dm", "onViewStateRestored ListFragment")
        savedInstanceState?.let {
            viewModel!!.restore(BundleWrapper.Base(savedInstanceState))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel?.let {
            viewModel!!.save(BundleWrapper.Base(outState))
        }
        Log.d("k0dm", "onSaveInstanceState ListFragment")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        Log.d("k0dm", "onDestroyView ListFragment")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("k0dm", "onDestroy ListFragment")
    }
}