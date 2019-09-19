package com.atiga.moviecataloguerl.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.utils.FakeDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel viewModel;
    private MainRepository mainRepository = mock(MainRepository.class);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new MovieViewModel(mainRepository);
    }

    @Test
    public void getMovies(){
        ArrayList<MovieResultsItem> dummyMovies = FakeDataDummy.generateDummyMovies();

        MutableLiveData<List<MovieResultsItem>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(mainRepository.getAllMovies()).thenReturn(movies);

        Observer<List<MovieResultsItem>> observer = mock(Observer.class);
        viewModel.getAllMovies().observeForever(observer);

        verify(observer).onChanged(dummyMovies);
    }

    @Test
    public void getMovieGenre(){
        ArrayList<MovieGenresItem> dummyGenre = FakeDataDummy.generateDummyMovieGenres();

        MutableLiveData<List<MovieGenresItem>> genre = new MutableLiveData<>();
        genre.setValue(dummyGenre);

        when(mainRepository.getMovieGenre()).thenReturn(genre);

        Observer<List<MovieGenresItem>> observer = mock(Observer.class);
        viewModel.getMovieGenre().observeForever(observer);

        verify(observer).onChanged(dummyGenre);
    }

}