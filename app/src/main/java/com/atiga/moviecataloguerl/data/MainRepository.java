package com.atiga.moviecataloguerl.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;

import java.util.List;

public class MainRepository implements MainDataSource {
    private volatile static MainRepository INSTANCE = null;
    private final NetworkRepository networkRepository;

    private MainRepository(@NonNull NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public static MainRepository getInstance(NetworkRepository networkRepository) {
        if (INSTANCE == null) {
            synchronized (MainRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainRepository(networkRepository);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<List<MovieResultsItem>> getAllMovies() {
        MutableLiveData<List<MovieResultsItem>> movies = new MutableLiveData<>();

        networkRepository.getAllMovies(new NetworkRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MovieResultsItem> movieResultsItems) {
                movies.postValue(movieResultsItems);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return movies;
    }

    @Override
    public LiveData<MovieResultsItem> getMovieWithId(int id) {
        MutableLiveData<MovieResultsItem> movie = new MutableLiveData<>();
        networkRepository.getAllMovies(new NetworkRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceived(List<MovieResultsItem> movieResultsItems) {
                for (int i = 0; i < movieResultsItems.size(); i++) {
                    if(movieResultsItems.get(i).getId()==id){
                        movie.postValue(movieResultsItems.get(i));
                        break;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return movie;
    }

    @Override
    public LiveData<List<TvShowResultsItem>> getAllTvShow() {
        MutableLiveData<List<TvShowResultsItem>> tvShows = new MutableLiveData<>();

        networkRepository.getAllTv(new NetworkRepository.LoadTvShowsCallback() {
            @Override
            public void onAllTvShowsReceived(List<TvShowResultsItem> tvShowResultsItems) {
                tvShows.postValue(tvShowResultsItems);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvShows;
    }

    @Override
    public LiveData<TvShowResultsItem> getTvShowWithId(int id) {
        MutableLiveData<TvShowResultsItem> tvshow = new MutableLiveData<>();
        networkRepository.getAllTv(new NetworkRepository.LoadTvShowsCallback() {
            @Override
            public void onAllTvShowsReceived(List<TvShowResultsItem> tvShowResultsItems) {
                for (int i = 0; i < tvShowResultsItems.size(); i++) {
                    if(tvShowResultsItems.get(i).getId()==id){
                        tvshow.postValue(tvShowResultsItems.get(i));
                        break;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return tvshow;
    }

    @Override
    public LiveData<List<MovieGenresItem>> getMovieGenre() {
        MutableLiveData<List<MovieGenresItem>> genres = new MutableLiveData<>();

        networkRepository.getMovieGenre(new NetworkRepository.LoadMovieGenreCallback() {
            @Override
            public void onAllMovieGenreReceived(List<MovieGenresItem> movieGenresItems) {
                genres.postValue(movieGenresItems);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return genres;
    }

    @Override
    public LiveData<List<TvShowGenresItem>> getTvShowGenre() {
        MutableLiveData<List<TvShowGenresItem>> genres = new MutableLiveData<>();

        networkRepository.getTvShowGenre(new NetworkRepository.LoadTvShowGenreCallback() {
            @Override
            public void onAllTvShowGenreReceived(List<TvShowGenresItem> tvShowGenresItems) {
                genres.postValue(tvShowGenresItems);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
        return genres;
    }
}
