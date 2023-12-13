package ru.easycode.zerotoheroandroidtdd.task19.data

import com.google.gson.annotations.SerializedName
import ru.easycode.zerotoheroandroidtdd.task19.UiState

data class SimpleResponse(
    @SerializedName("text")
    private val text: String
) {
    fun mapToUi(): UiState.ShowData = UiState.ShowData(text)
}