package org.calber.citiesweather;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.calber.citiesweather.models.CityWeather;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by calber on 2/2/18.
 */

class ApiController {

    private static final String APIURL = "http://api.openweathermap.org/data/2.5/";
    private static final String APPID = "c46d39dc717e79ed5430e0a5571b9770";
    private final ApiInterface api;

    public ApiController() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(APIURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        api = retrofit.create(ApiInterface.class);
    }

    /**
     * https://developer.android.com/training/testing/espresso/idling-resource.html
     */
    private void prepareIdlingResource() {
        if (Application.getApiIdlingResource() != null) {
            Application.apiIdlingResource.setIdleState(false);
        }
    }

    /**
     * https://developer.android.com/training/testing/espresso/idling-resource.html
     */
    private void removeIdlingResource() {
        if (Application.getApiIdlingResource() != null) {
            Application.apiIdlingResource.setIdleState(true);
        }
    }


    public Observable<CityWeather> weather(String city) {
        prepareIdlingResource();
        if (api == null) throw new RuntimeException("Initialize api before use");

        return api.weather(city, APPID)
                .doOnComplete(() -> removeIdlingResource())
                .subscribeOn(Schedulers.io());
    }


}
