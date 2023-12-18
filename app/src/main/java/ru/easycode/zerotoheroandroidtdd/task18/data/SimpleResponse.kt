package ru.easycode.zerotoheroandroidtdd.task18.data

import com.google.gson.annotations.SerializedName
import ru.easycode.zerotoheroandroidtdd.task18.UiState

data class SimpleResponse(
    @SerializedName("text")
    private val text: String
) {
    fun mapToUi(): UiState.ShowData = UiState.ShowData(text)
}