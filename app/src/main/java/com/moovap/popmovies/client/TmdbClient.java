package com.moovap.popmovies.client;

import com.moovap.popmovies.models.Discover;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jdanielnd on 12/12/16.
 */

public interface TmdbClient {

    String BASE_URL = "https://api.themoviedb.org/3/";

    @GET("discover/movie")
    Call<Discover> getDiscover(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page,
            @Query("sort_by") String sortBy,
            @Query("include_adult") boolean includeAdult,
            @Query("include_video") boolean includeVideo);
}
