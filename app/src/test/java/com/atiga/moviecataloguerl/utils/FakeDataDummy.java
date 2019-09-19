package com.atiga.moviecataloguerl.utils;

import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<MovieResultsItem> generateDummyMovies() {
        ArrayList<MovieResultsItem> movies = new ArrayList<>();
        movies.add(new MovieResultsItem(1,
                "Alita: Battle Angel",
                "Adventure",
                "This in alita's overview",
                "Alita's poster path",
                "Alita's backdrop path",
                "January 1, 2010",
                5.5));
        movies.add(new MovieResultsItem(2,
                "Avenger",
                "Action",
                "This in avenger's overview",
                "Avenger's poster path",
                "Avenger's backdrop path",
                "December 1, 2010",
                7.5));
        return movies;
    }

    public static ArrayList<TvShowResultsItem> generateDummyTvShows(){
        ArrayList<TvShowResultsItem> tvShows = new ArrayList<>();
        tvShows.add(new TvShowResultsItem(1,
                "Arrow",
                "Action",
                "October 10, 2012",
                "This is arrow's overview",
                "Arrow's poster path",
                "Arrow's backdrop path",
                6.0));
        tvShows.add(new TvShowResultsItem(2,
                "Naruto",
                "Anime",
                "February 15, 2012",
                "This is naruto's overview",
                "Naruto's poster path",
                "Naruto's backdrop path",
                7.0));
        return tvShows;
    }

    public static ArrayList<MovieGenresItem> generateDummyMovieGenres(){
        ArrayList<MovieGenresItem> movieGenresItems = new ArrayList<>();
        movieGenresItems.add(new MovieGenresItem("Action", 1));
        movieGenresItems.add(new MovieGenresItem("Drama", 2));
        movieGenresItems.add(new MovieGenresItem("Crime", 3));
        return movieGenresItems;
    }

    public static ArrayList<TvShowGenresItem> generateDummyTvGenres(){
        ArrayList<TvShowGenresItem> tvShowGenresItems = new ArrayList<>();
        tvShowGenresItems.add(new TvShowGenresItem("Family", 1));
        tvShowGenresItems.add(new TvShowGenresItem("Kids", 2));
        tvShowGenresItems.add(new TvShowGenresItem("Documentary", 3));
        return tvShowGenresItems;
    }

}
