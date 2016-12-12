package com.moovap.popmovies.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TmdbClientProvider {

    public TmdbClientProvider() {

    }

    public TmdbClient getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TmdbClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(TmdbClient.class);
    }
}
