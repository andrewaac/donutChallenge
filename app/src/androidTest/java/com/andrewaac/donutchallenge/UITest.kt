package com.andrewaac.donutchallenge

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andrewaac.donutchallenge.ui.screens.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun donutViewIsVisible() {
        onView(withId(R.id.donut)).check(matches(isDisplayed()))
    }

    @Test
    fun donutScoreIs514() {
        onView(withText("514")).check(matches(isDisplayed()))
    }

    @Test
    fun tappingDonutOpensNextScreen() {
        onView(withId(R.id.donut)).perform(click())
        onView(withText("Excellent")).check(matches(isDisplayed()))
        onView(withText("4")).check(matches(isDisplayed()))
    }
}
