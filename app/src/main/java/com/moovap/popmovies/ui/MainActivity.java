package com.moovap.popmovies.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.moovap.popmovies.R;
import com.moovap.popmovies.adapters.MoviesGridAdapter;
import com.moovap.popmovies.client.TmdbClient;
import com.moovap.popmovies.client.TmdbClientProvider;
import com.moovap.popmovies.models.Discover;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<Discover> {

    MoviesGridAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_grid);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MoviesGridAdapter(new Discover());
        recyclerView.setAdapter(mAdapter);

        TmdbClient client = new TmdbClientProvider().getClient();
        Call<Discover> call = client.getDiscover(
                "b610d075e7372d9d659c688134e1741f",
                "pt-BR",
                1,
                "popularity.desc",
                false,
                false
        );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Discover> call, Response<Discover> response) {
        Discover discover = response.body();
        mAdapter.setDiscover(discover);
    }

    @Override
    public void onFailure(Call<Discover> call, Throwable t) {
        Toast.makeText(this, getString(R.string.activity_main_error), Toast.LENGTH_SHORT).show();
    }
}
