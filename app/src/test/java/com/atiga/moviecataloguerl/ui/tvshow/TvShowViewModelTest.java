package com.atiga.moviecataloguerl.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;
import com.atiga.moviecataloguerl.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TvShowViewModel viewModel;
    private MainRepository mainRepository = mock(MainRepository.class);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        viewModel = new TvShowViewModel(mainRepository);
    }

    @Test
    public void getAllTvShows(){
        ArrayList<TvShowResultsItem> dummyTvs= FakeDataDummy.generateDummyTvShows();

        MutableLiveData<List<TvShowResultsItem>> tvs= new MutableLiveData<>();
        tvs.setValue(dummyTvs);

        when(mainRepository.getAllTvShow()).thenReturn(tvs);

        Observer<List<TvShowResultsItem>> observer = mock(Observer.class);
        viewModel.getAllTvShows().observeForever(observer);

        verify(observer).onChanged(dummyTvs);
    }

    @Test
    public void getTvShowGenre(){
        ArrayList<TvShowGenresItem> dummyGenre = FakeDataDummy.generateDummyTvGenres();

        MutableLiveData<List<TvShowGenresItem>> genre = new MutableLiveData<>();
        genre.setValue(dummyGenre);

        when(mainRepository.getTvShowGenre()).thenReturn(genre);

        Observer<List<TvShowGenresItem>> observer = mock(Observer.class);
        viewModel.getTvShowGenre().observeForever(observer);

        verify(observer).onChanged(dummyGenre);
    }
}