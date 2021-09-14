package com.digi.tmdb

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.digi.tmdb.base.view.MovieActivity
import com.digi.tmdb.feature.MovieEmptyFragment
import com.digi.tmdb.feature.moviedetail.MovieDetailFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieEmptyFragmentTest {


    @Test
    fun testFragmentEmpty() {
        val mockNavController = Mockito.mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<MovieEmptyFragment>()

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
//
        // Verify that performing a click prompts the correct Navigation action
        Espresso.onView(ViewMatchers.withId(R.id.movieEmptyFragmentTextView)).perform(ViewActions.click())
    }

    @Test
    fun waitFor() {
    }
}