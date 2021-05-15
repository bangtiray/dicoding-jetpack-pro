package com.bangtiray.movietv.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bangtiray.movietv.R
import com.bangtiray.movietv.extension.getStringDate
import com.bangtiray.movietv.utils.MovieDummy
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private val movieDummy = MovieDummy.movieDummy()
    private val movieTvDummy = MovieDummy.movieTVDummy()


    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieDummy.size
            )
        )
    }

    @Test
    fun openMovie() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieDummy.size
            )
        )
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.movieTitle)).check(matches(withText(movieDummy[0].mTitle)))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(
            matches(
                withText(
                    "Release Date :${
                        getStringDate(
                            movieDummy[0].releaseDate
                        )
                    }"
                )
            )
        )
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(withText(movieDummy[0].mPopularity.toString())))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(withText("${movieDummy[0].mVoteAverage}%")))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(withText(movieDummy[0].mOverView)))
    }

    @Test
    fun loadTvShow() {
        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                movieTvDummy.size
            )
        )
    }

    @Test
    fun openTvShow(){
        onView(withId(R.id.nav_movies_tv)).perform(click())
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.movieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.movieTitle)).check(matches(withText(movieTvDummy[2].mTitle)))
        onView(withId(R.id.releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.releaseDate)).check(
            matches(
                withText(
                    "Release Date :${
                        getStringDate(
                            movieTvDummy[2].releaseDate
                        )
                    }"
                )
            )
        )
        onView(withId(R.id.textPopularity)).check(matches(isDisplayed()))
        onView(withId(R.id.textPopularity)).check(matches(withText(movieTvDummy[2].mPopularity.toString())))
        onView(withId(R.id.voteCount)).check(matches(isDisplayed()))
        onView(withId(R.id.voteCount)).check(matches(withText("${movieTvDummy[2].mVoteAverage}%")))
        onView(withId(R.id.textOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.textOverview)).check(matches(withText(movieTvDummy[2].mOverView)))
    }
}