package com.moovap.popmovies.models;

import com.moovap.popmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class Discover {

    private int page;
    private List<Movie> results = new ArrayList<>();
    private int total_results;
    private int total_pages;

    public int getPage() {
        return getPage();
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotalResults() {
        return total_results;
    }

    public int getTotalPages() {
        return total_pages;
    }
}
