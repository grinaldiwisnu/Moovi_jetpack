package com.grinaldi.moovi.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.grinaldi.moovi.R
import com.grinaldi.moovi.ui.main.MainActivity
import com.grinaldi.moovi.utils.IdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(IdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun movie_list_loaded_successfully() {
        onView(withId(R.id.recycler_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.text_sad_movie)).check(matches(not(isDisplayed())))
    }


    @Test
    fun movie_detail_loaded_successfully() {
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.recycler_movie)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
            )
        }
        check_detail_components()
    }

    @Test
    fun tv_show_list_loaded_successfully() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recycler_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.text_sad_tv)).check(matches(not(isDisplayed())))
    }

    @Test
    fun tv_show_detail_loaded_successfully() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recycler_tv)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
            )
        }
        check_detail_components()
    }

    @Test
    fun favorite_movies_empty_list() {
        val expectedErrorMessage = "No Data"
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.text_sad)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(expectedErrorMessage)))
        }
    }

    @Test
    fun favorite_tv_shows_empty_list() {
        val expectedErrorMessage = "No Data"
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.text_sad)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(expectedErrorMessage)))
        }
    }

    @Test
    fun add_favorite_movie_and_check_if_it_exists_in_favorite_list() {
        val expectedErrorMessage = "No Data"
        onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.recycler_movie)).apply {
            check(matches(isDisplayed()))
            perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
            )
        }
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.text_sad)).apply {
            check(matches(not(isDisplayed())))
        }
        onView(withId(R.id.recycler_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun add_favorite_tv_show_and_check_if_it_exists_in_favorite_list() {
        val expectedErrorMessage = "No Data"
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recycler_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.text_sad)).apply {
            check(matches(not(isDisplayed())))
        }
        onView(withId(R.id.recycler_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.fab)).perform(click())
        onView(isRoot()).perform(pressBack())
    }

    private fun check_detail_components() {
        onView(withId(R.id.smallPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovieGenre)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvMovieTagLine)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.errorMessage)).check(matches(not(isDisplayed())))
    }
}