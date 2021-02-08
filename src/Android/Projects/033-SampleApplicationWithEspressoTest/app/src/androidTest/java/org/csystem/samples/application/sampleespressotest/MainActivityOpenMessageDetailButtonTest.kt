package org.csystem.samples.application.sampleespressotest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityOpenMessageDetailButtonTest {
    @Rule
    @JvmField
    var mainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureMessageDetailActivityGetMessage()
    {
        val text = "csd"
        val expectedText = "CSD"

        onView(withId(R.id.mainActivityEditTextMessage)).perform(typeText(text), closeSoftKeyboard())

        //Reverse Button'una bas
        onView(withId(R.id.mainActivityButtonOpenMessageDetailActivity)).perform(click())

        onView(withId(R.id.messageDetailActivityEditTextMessage)).check(matches(withText(expectedText)))
    }
}