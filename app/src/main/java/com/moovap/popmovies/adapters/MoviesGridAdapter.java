package com.moovap.popmovies.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moovap.popmovies.R;
import com.moovap.popmovies.models.Discover;
import com.moovap.popmovies.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jdanielnd on 12/12/16.
 */

public class MoviesGridAdapter extends RecyclerView.Adapter<MoviesGridAdapter.ViewHolder> {

    private List<Movie> mDataset;

    public MoviesGridAdapter(Discover discover) {
        mDataset = discover.getResults();
    }

    public void setDiscover(Discover discover) {
        mDataset = discover.getResults();
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
        Movie movie = mDataset.get(position);
        Picasso.with(holder.itemView.getContext())
                .load(movie.getPosterUrl())
                .into(holder.movieThumbnail);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            movieThumbnail = (ImageView) itemView.findViewById(R.id.movie_thumbnail);
        }
    }
}
