package com.atiga.moviecataloguerl.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private MainRepository mainRepository;

    public TvShowViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    LiveData<List<TvShowResultsItem>> getAllTvShows() {
        return mainRepository.getAllTvShow();
    }

    LiveData<List<TvShowGenresItem>> getTvShowGenre() {
        return mainRepository.getTvShowGenre();
    }
}
