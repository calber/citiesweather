package org.calber.citiesweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnItemSelected {

    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("10 cities");


        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        list.setLayoutManager(layoutManager1);
        list.setHasFixedSize(true);

        CitiesAdapter adapter = new CitiesAdapter(this);
        list.setAdapter(adapter);
    }

    @Override
    public void onDataReady(String city) {
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("city",city);
        startActivity(intent);
    }

    private class CitiesAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final OnItemSelected listener;

        public CitiesAdapter(OnItemSelected l) {
            listener = l;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            View view = inflater.inflate(R.layout.city, parent, false);
            return new ViewHolder(view, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.name.setText(Application.cities[position]);
            holder.root.setOnClickListener(v -> listener.onDataReady(Application.cities[position]));
        }


        @Override
        public int getItemCount() {
            return Application.cities.length;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        public TextView name;
        @BindView(R.id.root)
        public View root;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(view -> {

            });
        }

    }
}
