package org.calber.citiesweather;

import org.calber.citiesweather.models.CityWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by calber on 2/2/18.
 */

interface ApiInterface {
    @GET("weather")
    Observable<CityWeather> weather(@Query("q") String city, @Query("APPID") String key);
}
