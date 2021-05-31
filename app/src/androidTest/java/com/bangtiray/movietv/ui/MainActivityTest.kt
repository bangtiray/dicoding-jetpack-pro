package com.bangtiray.movietv.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
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
    fun test1LoadMovieAndTvShow() {
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )

        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                5
            )
        )

        onView(withId(R.id.nav_movies)).perform(click())
    }

    @Test
    fun test2ViewTest() {
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText(R.string.tab_title_movie)).perform(click())
        onView(withText(R.string.tab_title_tvshow)).perform(click())
        onView(withId(R.id.nav_movies)).perform(click())
    }

    @Test
    fun test3InsertAndUpdateFavorite() {
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        pressBack()

        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                5,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        pressBack()

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        pressBack()

        onView(withId(R.id.nav_favorite)).perform(click())
        onView(withText(R.string.tab_title_tvshow)).perform(click())
        onView(withId(R.id.fav_recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.favoriteButton)).perform(click())
        pressBack()

    }

    @Test
    fun test4DetailMovie() {
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(2))
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    2,
                    click()
                )
            )

        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun test5DetailTvShow() {
        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4))
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    4,
                    click()
                )
            )

        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
        pressBack()
    }
}