package ru.easycode.zerotoheroandroidtdd.task19

import ru.easycode.zerotoheroandroidtdd.task19.data.SimpleResponse

interface LoadResult {

    fun show(updateLiveData: LiveDataWrapper.Update)

    data class Success(private val data: SimpleResponse) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            updateLiveData.update(data.mapToUi())
        }
    }

    data class Error(private val noConnection: Boolean) : LoadResult {
        override fun show(updateLiveData: LiveDataWrapper.Update) {
            val error = if (noConnection) "No internet connection" else "Something went wrong"
            updateLiveData.update(UiState.ShowData(error))
        }
    }
}
