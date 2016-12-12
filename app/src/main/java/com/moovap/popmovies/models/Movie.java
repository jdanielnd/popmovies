package com.moovap.popmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Parcelable {

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    private String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    private String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    private String getBackdropPath() {
        return backdropPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getPosterUrl() {
        return "http://image.tmdb.org/t/p/w342" + getPosterPath();
    }

    public String getBackdropUrl() {
        return "http://image.tmdb.org/t/p/w780" + getBackdropPath();
    }

    public String getFormattedDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String formattedReleaseDate = getReleaseDate();
        try {
            Date date = format.parse(getReleaseDate());

            SimpleDateFormat finalFormat = new SimpleDateFormat("MMM YYYY");
            formattedReleaseDate = finalFormat.format(date);
            formattedReleaseDate= formattedReleaseDate.substring(0,1).toUpperCase() +
                    formattedReleaseDate.substring(1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedReleaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.posterPath);
        dest.writeString(this.overview);
        dest.writeString(this.releaseDate);
        dest.writeString(this.originalTitle);
        dest.writeString(this.title);
        dest.writeString(this.backdropPath);
        dest.writeValue(this.voteAverage);
    }

    private Movie(Parcel in) {
        this.posterPath = in.readString();
        this.overview = in.readString();
        this.releaseDate = in.readString();
        this.originalTitle = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.voteAverage = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}