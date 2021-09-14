package com.digi.tmdb

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.digi.tmdb.base.view.MovieActivity
import net.bytebuddy.matcher.ElementMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

import androidx.test.espresso.Espresso.onData

import androidx.test.espresso.Espresso.onData
import android.view.ViewGroup
import androidx.test.espresso.*
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToHolder
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.android.dx.dex.file.Item
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.mockito.ArgumentMatchers.matches


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get : Rule
    val activityRule = ActivityScenarioRule(MovieActivity::class.java)

    @Test
    fun Searchfuntionality() {
        onView(withId(R.id.search_bar))
            .perform(ViewActions.typeText("Malignant"), ViewActions.closeSoftKeyboard())
        onView(ViewMatchers.isRoot()).perform(waitFor(5000))
        onView(
            nthChildOf(
                withId(R.id.rv_movie_list),
                0
            )
        ).check(ViewAssertions.matches(hasDescendant(withText("Malignant")))).perform(click())


//

    }

    @Test
    fun InteractionWithRecyclerView() {

       onView(withId(R.id.rv_movie_list)).perform(
           RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
               hasDescendant(withText("Malignant")),
               click())

        )


//        onView(viewMatcher)
//            .perform(scrollToPosition(i))
//            .check( RecyclerItemViewAssertion<>(i, items.get(i), new ItemViewAssertion<Item>() {
//                @Override
//                public void check(Item item, View view, NoMatchingViewException e) {
//                    matches(hasDescendant(withText(item.getDisplayName())))
//                        .check(view, e);
//                }
//            }));


    }



    fun nthChildOf(parentMatcher: Matcher<View?>, childPosition: Int): Matcher<View?>? {
        return object : TypeSafeMatcher<View?>() {
            override fun describeTo(description: Description) {
                description.appendText("with $childPosition child view of type parentMatcher")
            }


            override fun matchesSafely(view: View?): Boolean {
                if (view?.parent !is ViewGroup) {
                    return parentMatcher.matches(view?.parent)
                }
                val group = view?.parent as ViewGroup
                return parentMatcher.matches(view?.parent) && group.getChildAt(childPosition) == view
            }
        }
    }


    @Test
    fun sampleTesting() {
        onView(ViewMatchers.isRoot()).perform(waitFor(5000))
        onView(withId(R.id.rv_movie_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )
        onView(withId(R.id.ib_average_vote)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(click())
    }

    fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController?, view: View?) {
                uiController?.loopMainThreadForAtLeast(delay)
            }

            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }
        }
    }
}