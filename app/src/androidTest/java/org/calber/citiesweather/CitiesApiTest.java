package org.calber.citiesweather;

import org.calber.citiesweather.models.CityWeather;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.calber.citiesweather.Application.cities;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class CitiesApiTest {


    @Test
    public void api() throws Exception {
        ApiController controller = new ApiController();

        for (String c : cities) {
            CityWeather res = controller.weather("London").blockingLast();
            assertNotNull(res);
        }
    }
}
