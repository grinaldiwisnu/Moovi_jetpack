package com.grinaldi.moovi.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.grinaldi.moovi.R
import com.grinaldi.moovi.data.sources.MockMovieSource
import com.grinaldi.moovi.utils.IdlingResource
import com.grinaldi.moovi.views.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private var dataDummy: MockMovieSource? = null

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.espressoTestIdlingResource)
        dataDummy = MockMovieSource
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText("Moovi")))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataDummy?.getAllMovies()?.size!!
            )
        )
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
        onView(withId(R.id.tv_title)).check(
            matches(
                withText(
                    "${
                        dataDummy?.getAllMovies()?.get(0)?.title
                    } (${dataDummy?.getAllMovies()?.get(0)?.date})"
                )
            )
        )
        onView(withId(R.id.tv_overview)).check(
            matches(
                withText(
                    dataDummy?.getAllMovies()?.get(0)?.description
                )
            )
        )
        onView(withId(R.id.tv_score)).check(
            matches(
                withText(
                    dataDummy?.getAllMovies()?.get(0)?.rating + "/100"
                )
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText("Moovi")))
        onView(withId(R.id.rv_tvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataDummy?.getAllTvShows()?.size!!
            )
        )
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
        onView(withId(R.id.tv_title)).check(
            matches(
                withText(
                    "${
                        dataDummy?.getAllTvShows()?.get(0)?.title
                    } (${dataDummy?.getAllTvShows()?.get(0)?.date})"
                )
            )
        )
        onView(withId(R.id.tv_overview)).check(
            matches(
                withText(
                    dataDummy?.getAllTvShows()?.get(0)?.description
                )
            )
        )
        onView(withId(R.id.tv_score)).check(
            matches(
                withText(
                    dataDummy?.getAllTvShows()?.get(0)?.rating + "/100"
                )
            )
        )
    }
}