package ru.easycode.zerotoheroandroidtdd.task26

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.easycode.zerotoheroandroidtdd.R

class MainPage : AbstractPage(R.id.rootLayout) {

    fun clickAddButton() {
        onView(withId(R.id.addButton)).perform(click())
    }

    fun checkItem(position: Int, text: String) {
        onView(
            RecyclerViewMatcher(R.id.recyclerView).atPosition(
                position,
                R.id.elementTextView
            )
        ).check(matches(withText(text)))
    }
}