package com.bangtiray.movietv.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bangtiray.movietv.R
import com.bangtiray.movietv.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovieAndTVShow() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                3
            )
        )

        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                6
            )
        )
    }

    @Test
    fun tvShowInstrumentTest() {
        onView(withId(R.id.nav_movies)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
    }

    @Test
    fun movieShowInstrumentTest() {
        onView(withId(R.id.nav_movies)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                6,
                click()
            )
        )
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
    }
}