package com.digi.tmdb

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.digi.tmdb.feature.moviedetail.MovieDetailFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {


    @Test
    fun testFragmentDetail()
    {
        val mockNavController = Mockito.mock(NavController::class.java)

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<MovieDetailFragment>(

        )

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }

        // Verify that performing a click prompts the correct Navigation action
        Espresso.onView(ViewMatchers.withId(R.id.ib_average_vote)).perform(ViewActions.click())
    }

    @Test
    fun waitFor() {
    }
}