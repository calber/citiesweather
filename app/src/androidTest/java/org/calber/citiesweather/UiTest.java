package org.calber.citiesweather;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UiTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    private IdlingResource mIdlingResource1;

    @Before
    public void registerIdlingResource() {
        mIdlingResource1 = Application.getApiIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource1);
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource1 != null) {
            Espresso.unregisterIdlingResources(mIdlingResource1);
        }
    }

    @Test
    public void test() {
        onView(allOf(withId(R.id.root),
                childAtPosition(childAtPosition(withId(R.id.list), 0), 0),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1),
                isDisplayed())).check(matches(withText("London")));
        pressBack();

        onView(allOf(withId(R.id.root), childAtPosition(
                childAtPosition(withId(R.id.list), 1), 0),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1),
                isDisplayed())).check(matches(withText("Glasgow")));
        pressBack();

        onView(allOf(withId(R.id.root), childAtPosition(
                childAtPosition(withId(R.id.list), 3), 0),
                isDisplayed())).perform(click());

        onView(allOf(withId(R.id.name), childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1),
                isDisplayed())).check(matches(withText("Toronto")));
        pressBack();


    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
