package com.moovap.popmovies.ui;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.moovap.popmovies.BuildConfig;
import com.moovap.popmovies.R;
import com.moovap.popmovies.adapters.MoviesGridAdapter;
import com.moovap.popmovies.client.TmdbClient;
import com.moovap.popmovies.client.TmdbClientProvider;
import com.moovap.popmovies.models.MovieCollection;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<MovieCollection> {

    MoviesGridAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_grid);

        RecyclerView.LayoutManager layoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 2);
        } else {
            layoutManager = new GridLayoutManager(this, 3);
        }

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MoviesGridAdapter(new MovieCollection());
        recyclerView.setAdapter(mAdapter);

        updatePopular();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_popular:
                updatePopular();
                return true;
            case R.id.action_top_rated:
                updateTopRated();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateTopRated() {
        TmdbClient client = new TmdbClientProvider().getClient();
        Call<MovieCollection> call = client.getTopRatedMovies(
                BuildConfig.THE_MOVIE_DB_API_TOKEN,
                getLanguage(),
                1
        );
        call.enqueue(this);
    }

    private void updatePopular() {
        TmdbClient client = new TmdbClientProvider().getClient();
        Call<MovieCollection> call = client.getPopularMovies(
                BuildConfig.THE_MOVIE_DB_API_TOKEN,
                getLanguage(),
                1
        );

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieCollection> call, Response<MovieCollection> response) {
        MovieCollection movieCollection = response.body();
        mAdapter.setMovieCollection(movieCollection);
    }

    @Override
    public void onFailure(Call<MovieCollection> call, Throwable t) {
        Toast.makeText(this, getString(R.string.activity_main_error), Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return getResources().getConfiguration().getLocales().get(0);
        } else{
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }

    public String getLanguage() {
        return getCurrentLocale().getLanguage();
    }

}
