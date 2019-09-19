package com.atiga.moviecataloguerl.ui.movie;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;

import java.util.ArrayList;
import java.util.List;

public class MovieViewModel extends ViewModel {
    private MainRepository mainRepository;

    public MovieViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    LiveData<List<MovieResultsItem>> getAllMovies() {
        return mainRepository.getAllMovies();
    }

    LiveData<List<MovieGenresItem>> getMovieGenre() {
        return mainRepository.getMovieGenre();
    }

}
