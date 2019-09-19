package com.atiga.moviecataloguerl.utils;

import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.ArrayList;

public class FakeDataDummy {
    public static ArrayList<MovieResultsItem> generateDummyMovies() {
        ArrayList<MovieResultsItem> movies = new ArrayList<>();
        movies.add(new MovieResultsItem(399579,
                "Alita: Battle Angel",
                "Action, Adventure, Science Fiction, Thriller",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "/xRWht48C2V8XNfzvPehyClOvDni.jpg",
                "/8yjqnpOfuFQg3qLRl9Ca1NgIBB5.jpg",
                "2019-02-14",
                6.8));
        movies.add(new MovieResultsItem(299534,
                "Avenger",
                "Action",
                "This in avenger's overview",
                "/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                "/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
                "2010-12-01",
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
                "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg",
                "/dKxkwAJfGuznW8Hu0mhaDJtna0n.jpg",
                6.0));
        tvShows.add(new TvShowResultsItem(2,
                "Naruto",
                "Anime",
                "February 15, 2012",
                "This is naruto's overview",
                "/zAYRe2bJxpWTVrwwmBc00VFkAf4.jpg",
                "/c14vjmndzL9tBdooGsMznMFrFLo.jpg",
                7.0));
        return tvShows;
    }
}
