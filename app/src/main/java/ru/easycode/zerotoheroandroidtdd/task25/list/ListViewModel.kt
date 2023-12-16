package ru.easycode.zerotoheroandroidtdd.task25.list

import androidx.lifecycle.ViewModel
import ru.easycode.zerotoheroandroidtdd.task25.core.BundleWrapper
import ru.easycode.zerotoheroandroidtdd.task25.core.ListLiveDataWrapper
import ru.easycode.zerotoheroandroidtdd.task25.core.Navigation
import ru.easycode.zerotoheroandroidtdd.task25.create.CreateScreen

class ListViewModel(
    private val liveDataWrapper: ListLiveDataWrapper.Mutable,
    private val navigation: Navigation.Update,
) : ViewModel() {

    fun liveData() = liveDataWrapper.liveData()

    fun create() {
        navigation.update(CreateScreen)
    }

    fun save(bundleWrapper: BundleWrapper.Save) {
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore) =
        liveDataWrapper.update(bundleWrapper.restore())
}
