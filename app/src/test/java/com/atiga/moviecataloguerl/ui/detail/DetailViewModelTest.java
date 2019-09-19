package com.atiga.moviecataloguerl.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.atiga.moviecataloguerl.data.MainRepository;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;
import com.atiga.moviecataloguerl.utils.FakeDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailViewModel viewModel;
    private MainRepository mainRepository = mock(MainRepository.class);
    private MovieResultsItem dummyMovie = FakeDataDummy.generateDummyMovies().get(0);
    private TvShowResultsItem dummyTv = FakeDataDummy.generateDummyTvShows().get(0);
    private List<MovieGenresItem> dummyMovieGenre = FakeDataDummy.generateDummyMovieGenres();
    private List<TvShowGenresItem> dummyTvGenre = FakeDataDummy.generateDummyTvGenres();
    private int movieId = dummyMovie.getId();
    private int tvId = dummyTv.getId();

    @Before
    public void setUp(){
        viewModel = new DetailViewModel(mainRepository);
        viewModel.setMovieId(movieId);
        viewModel.setTvId(tvId);
    }

    @Test
    public void getMovie(){
        MutableLiveData<MovieResultsItem> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(mainRepository.getMovieWithId(movieId)).thenReturn(movie);

        Observer<MovieResultsItem> observer = mock(Observer.class);

        viewModel.getMovieWithId().observeForever(observer);

        verify(observer).onChanged(dummyMovie);
    }

    @Test
    public void getTvShow(){
        MutableLiveData<TvShowResultsItem> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTv);

        when(mainRepository.getTvShowWithId(tvId)).thenReturn(tvShow);

        Observer<TvShowResultsItem> observer = mock(Observer.class);

        viewModel.getTvShowWithId().observeForever(observer);

        verify(observer).onChanged(dummyTv);
    }

    @Test
    public void getMovieGenre(){
        MutableLiveData<List<MovieGenresItem>> genre = new MutableLiveData<>();
        genre.setValue(dummyMovieGenre);

        when(mainRepository.getMovieGenre()).thenReturn(genre);

        Observer<List<MovieGenresItem>> observer = mock(Observer.class);

        viewModel.getMovieGenre().observeForever(observer);

        verify(observer).onChanged(dummyMovieGenre);

    }

    @Test
    public void getTvShowGenre(){
        MutableLiveData<List<TvShowGenresItem>> genre = new MutableLiveData<>();
        genre.setValue(dummyTvGenre);

        when(mainRepository.getTvShowGenre()).thenReturn(genre);

        Observer<List<TvShowGenresItem>> observer = mock(Observer.class);

        viewModel.getTvShowGenre().observeForever(observer);

        verify(observer).onChanged(dummyTvGenre);
    }

}