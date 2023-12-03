package ru.easycode.zerotoheroandroidtdd

interface Count {

    fun increment(number: String): String

    class Base(private val step: Int) : Count {

        init {
            if (step < 1) throw IllegalStateException("step should be positive, but was $step")
        }

        override fun increment(number: String) = (number.toInt() + step).toString()
    }
}
