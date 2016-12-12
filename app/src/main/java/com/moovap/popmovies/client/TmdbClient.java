package com.moovap.popmovies.client;

import com.moovap.popmovies.models.MovieCollection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TmdbClient {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("movie/popular")
    Call<MovieCollection> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);

    @GET("movie/top_rated")
    Call<MovieCollection> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page);
}
