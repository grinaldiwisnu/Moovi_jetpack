package com.grinaldi.moovi.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.grinaldi.moovi.R
import com.grinaldi.moovi.ui.main.MainActivity
import com.grinaldi.moovi.utils.IdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.recycler_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.title_app)).check(matches(withText("Moovi")))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.recycler_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvMovieTagLine)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailHomePage)).check(matches(isDisplayed()))
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recycler_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.title_app)).check(matches(withText("Moovi")))
    }

    @Test
    fun loadTvShowDetail() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recycler_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tvMovieTagLine)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailHomePage)).check(matches(isDisplayed()))
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}