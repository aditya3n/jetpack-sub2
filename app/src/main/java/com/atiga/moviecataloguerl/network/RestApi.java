package com.atiga.moviecataloguerl.network;

import com.atiga.moviecataloguerl.model.MovieGenreResponse;
import com.atiga.moviecataloguerl.model.MovieResponse;
import com.atiga.moviecataloguerl.model.TvShowGenreResponse;
import com.atiga.moviecataloguerl.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApi {
    @GET("discover/movie")
    Call<MovieResponse> getListMovies(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("discover/tv")
    Call<TvShowResponse> getListTvShow(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("genre/movie/list")
    Call<MovieGenreResponse> getMovieGenre(@Query("api_key") String apiKey, @Query("language") String language);

    @GET("genre/tv/list")
    Call<TvShowGenreResponse> getTvShowGenre(@Query("api_key") String apiKey, @Query("language") String language);

}
