package com.moovap.popmovies.client;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jdanielnd on 12/12/16.
 */

public class TmdbClientProvider {

    public TmdbClientProvider() {

    }

    public TmdbClient getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TmdbClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TmdbClient client = retrofit.create(TmdbClient.class);

        return  client;
    }
}
