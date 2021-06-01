package com.grinaldi.moovi

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.grinaldi.moovi.utils.IdlingResource
import com.grinaldi.moovi.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ExampleInstrumentedTest {
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
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowDetail() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                )
        )
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
    }
}