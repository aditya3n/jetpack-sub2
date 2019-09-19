package com.atiga.moviecataloguerl.data;

import com.atiga.moviecataloguerl.BuildConfig;
import com.atiga.moviecataloguerl.model.MovieGenreResponse;
import com.atiga.moviecataloguerl.model.MovieGenresItem;
import com.atiga.moviecataloguerl.model.MovieResponse;
import com.atiga.moviecataloguerl.model.MovieResultsItem;
import com.atiga.moviecataloguerl.model.TvShowGenreResponse;
import com.atiga.moviecataloguerl.model.TvShowGenresItem;
import com.atiga.moviecataloguerl.model.TvShowResponse;
import com.atiga.moviecataloguerl.model.TvShowResultsItem;
import com.atiga.moviecataloguerl.network.MyClient;
import com.atiga.moviecataloguerl.network.RestApi;
import com.atiga.moviecataloguerl.utils.EspressoIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {
    private static NetworkRepository INSTANCE;
    private final RestApi mApi;

    public NetworkRepository() {
        mApi = MyClient.getClient();
    }

    public static NetworkRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NetworkRepository();
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadMoviesCallback callback) {
        EspressoIdlingResource.increment();
        mApi.getListMovies(BuildConfig.API_KEY, "en-US").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                EspressoIdlingResource.decrement();
                if (response.isSuccessful() && response.body() != null) {
                    callback.onAllMoviesReceived(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getAllTv(LoadTvShowsCallback callback) {
        EspressoIdlingResource.increment();
        mApi.getListTvShow(BuildConfig.API_KEY, "en-US").enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                EspressoIdlingResource.decrement();
                if (response.isSuccessful() && response.body() != null) {
                    callback.onAllTvShowsReceived(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getMovieGenre(LoadMovieGenreCallback callback) {
        EspressoIdlingResource.increment();
        mApi.getMovieGenre(BuildConfig.API_KEY, "en-US").enqueue(new Callback<MovieGenreResponse>() {
            @Override
            public void onResponse(Call<MovieGenreResponse> call, Response<MovieGenreResponse> response) {
                EspressoIdlingResource.decrement();
                if (response.isSuccessful() && response.body() != null) {
                    callback.onAllMovieGenreReceived(response.body().getGenres());
                }
            }

            @Override
            public void onFailure(Call<MovieGenreResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        });
    }

    public void getTvShowGenre(LoadTvShowGenreCallback callback) {
        EspressoIdlingResource.increment();
        mApi.getTvShowGenre(BuildConfig.API_KEY, "en-US").enqueue(new Callback<TvShowGenreResponse>() {
            @Override
            public void onResponse(Call<TvShowGenreResponse> call, Response<TvShowGenreResponse> response) {
                EspressoIdlingResource.decrement();
                if (response.isSuccessful() && response.body() != null) {
                    callback.onAllTvShowGenreReceived(response.body().getGenres());
                }
            }

            @Override
            public void onFailure(Call<TvShowGenreResponse> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        });
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceived(List<MovieResultsItem> movieResultsItems);

        void onDataNotAvailable();
    }

    public interface LoadTvShowsCallback {
        void onAllTvShowsReceived(List<TvShowResultsItem> tvShowResultsItems);

        void onDataNotAvailable();
    }

    public interface LoadMovieGenreCallback {
        void onAllMovieGenreReceived(List<MovieGenresItem> movieGenresItems);

        void onDataNotAvailable();
    }

    public interface LoadTvShowGenreCallback {
        void onAllTvShowGenreReceived(List<TvShowGenresItem> tvShowGenresItems);

        void onDataNotAvailable();
    }


}
