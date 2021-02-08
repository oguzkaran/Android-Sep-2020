package org.csystem.samples.application.sampleespressotest

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Assert.*
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class MainActivityReverseButtonTest {
    @Rule
    @JvmField
    var mainActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun ensureTextReversed()
    {
        val text = "csd"
        val expectedText = "dsc"

        //EditText'e yazıyı yaz ve klavyeyi kapat
        onView(withId(R.id.mainActivityEditTextMessage)).perform(typeText(text), closeSoftKeyboard())

        //Reverse Button'una bas
        onView(withId(R.id.mainActivityButtonReverse)).perform(click())

        //Yazıyı test et
        onView(withId(R.id.mainActivityEditTextMessage)).check(matches(withText(expectedText)))
    }

}