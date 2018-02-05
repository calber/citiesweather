package org.calber.citiesweather;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

/**
 * Created by calber on 2/2/18.
 */

public class Application extends android.app.Application {
    public static SimpleIdlingResource apiIdlingResource;

    public static final String cities[] = {"London", "Glasgow", "Manchester", "Toronto", "Rome", "Milan", "Paris", "Amsterdam", "Moscow", "Sidney"};

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public static IdlingResource getApiIdlingResource() {
        if (apiIdlingResource == null) {
            apiIdlingResource = new SimpleIdlingResource();
        }
        return apiIdlingResource;
    }

}
