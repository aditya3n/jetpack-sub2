package com.atiga.moviecataloguerl.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.List;

public class DetailViewModel extends ViewModel {
    private int movieId, tvId;
    private MainRepository mainRepository;

    public DetailViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    LiveData<MovieResultsItem> getMovieWithId() {
        return mainRepository.getMovieWithId(movieId);
    }

    LiveData<TvShowResultsItem> getTvShowWithId() {
        return mainRepository.getTvShowWithId(tvId);
    }

    LiveData<List<MovieGenresItem>> getMovieGenre() {
        return mainRepository.getMovieGenre();
    }

    LiveData<List<TvShowGenresItem>> getTvShowGenre() {
        return mainRepository.getTvShowGenre();
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }
}
