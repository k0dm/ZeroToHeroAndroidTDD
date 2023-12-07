package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun increment(number: String): UiState

    fun decrement(number: String): UiState

    fun initial(number: String): UiState

    class Base(private val step: Int, private val max: Int, private val min: Int) : Count {

        init {
            if (step <= 0) throw IllegalStateException("step should be positive, but was $step")
            if (max < 0) throw IllegalStateException("max should be positive, but was $max")
            if (max <= step) throw IllegalStateException("max should be more than step")
            if (max <= min) throw IllegalStateException("max should be more than min")
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            val resultStr = result.toString()
            return if (result >= max) UiState.Max(resultStr)
            else UiState.Base(resultStr)
        }

        override fun decrement(number: String): UiState {
            val result = number.toInt() - step
            val resultStr = result.toString()
            return if (result <= min) UiState.Min(resultStr)
            else UiState.Base(resultStr)
        }

        override fun initial(number: String) = when (number.toInt()) {
            min -> UiState.Min(number)
            max -> UiState.Max(number)
            else -> UiState.Base(number)
        }
    }


}


