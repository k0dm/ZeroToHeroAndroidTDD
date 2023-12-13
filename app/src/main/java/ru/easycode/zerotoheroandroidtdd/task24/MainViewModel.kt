package ru.easycode.zerotoheroandroidtdd.task24

class MainViewModel(private val listLiveDataWrapper: ListLiveDataWrapper) {

    fun add(text: CharSequence) = listLiveDataWrapper.add(text)

    fun liveData() = listLiveDataWrapper.liveData()

    fun save(bundle: BundleWrapper.Save) = listLiveDataWrapper.save(bundle)

    fun restore(bundle: BundleWrapper.Restore) = listLiveDataWrapper.update(bundle.restore())
}