package com.atiga.moviecataloguerl.data;

import androidx.lifecycle.LiveData;

import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.List;

public interface MainDataSource {
    LiveData<List<MovieResultsItem>> getAllMovies();
    LiveData<MovieResultsItem> getMovieWithId(int id);
    LiveData<List<TvShowResultsItem>> getAllTvShow();
    LiveData<TvShowResultsItem> getTvShowWithId(int id);
    LiveData<List<MovieGenresItem>> getMovieGenre();
    LiveData<List<TvShowGenresItem>> getTvShowGenre();

}
