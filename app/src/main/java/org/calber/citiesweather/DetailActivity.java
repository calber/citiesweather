package org.calber.citiesweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.sunrise)
    TextView sunrise;
    @BindView(R.id.overcast)
    TextView overcast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ButterKnife.bind(this);

        String city = getIntent().getStringExtra("city");

        ApiController controller = new ApiController();

        controller.weather(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cw -> {
                    name.setText(cw.name);
                    sunrise.setText(new Date(cw.sys.sunrise).toString());
                    overcast.setText(cw.weather.get(0).description);

                }, throwable -> Log.e(this.getClass().getName(), "error", throwable));
    }
}
