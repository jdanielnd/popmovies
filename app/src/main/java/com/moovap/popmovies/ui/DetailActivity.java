package com.moovap.popmovies.ui;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.moovap.popmovies.R;
import com.moovap.popmovies.adapters.MoviesGridAdapter;
import com.moovap.popmovies.models.Movie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Movie movie = getIntent().getParcelableExtra(MoviesGridAdapter.MOVIE);

        ImageView movieBackdrop = (ImageView) findViewById(R.id.movie_backdrop);

        collapsingToolbar.setTitle(movie.getTitle());
        Picasso.with(this).load(movie.getBackdropUrl()).into(movieBackdrop);

        TextView overview = (TextView) findViewById(R.id.overview);
        overview.setText(movie.getOverview());

        TextView originalTitle = (TextView) findViewById(R.id.originalTitle);
        originalTitle.setText(movie.getOriginalTitle());

        TextView releaseDate = (TextView) findViewById(R.id.releaseDate);
        releaseDate.setText(movie.getFormattedDate());

        TextView averageRating = (TextView) findViewById(R.id.averageRating);
        averageRating.setText(getString(R.string.average_rating, movie.getVoteAverage()));

        ImageView movieThumbnail = (ImageView) findViewById(R.id.movie_thumbnail);
        Picasso.with(this).load(movie.getPosterUrl()).into(movieThumbnail);


    }
}
