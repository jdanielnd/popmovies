package com.moovap.popmovies.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moovap.popmovies.R;
import com.moovap.popmovies.models.MovieCollection;
import com.moovap.popmovies.models.Movie;
import com.moovap.popmovies.ui.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.ViewHolder> {

    public final static String MOVIE = "MOVIE";

    private List<Movie> mDataset;

    public MoviesGridAdapter(MovieCollection movieCollection) {
        mDataset = movieCollection.getResults();
    }

    public void setMovieCollection(MovieCollection movieCollection) {
        mDataset = movieCollection.getResults();
        this.notifyDataSetChanged();
    }

    @Override
    public MoviesGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesGridAdapter.ViewHolder holder, int position) {
        final Movie movie = mDataset.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(movie.getPosterUrl())
                .into(holder.movieThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(MOVIE, movie);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieThumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movie_thumbnail);
        }
    }
}
