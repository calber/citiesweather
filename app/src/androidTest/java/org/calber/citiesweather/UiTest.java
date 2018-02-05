package org.calber.citiesweather;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

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

        for (int i = 0; i < Application.cities.length; i++) {
            onView(ViewMatchers.withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));
            onView(allOf(withId(R.id.name), isDisplayed())).check(matches(withText(Application.cities[i])));
            pressBack();
        }

    }
}
