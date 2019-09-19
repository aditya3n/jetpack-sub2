package com.atiga.moviecataloguerl.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowGenreResponse{

	@SerializedName("genres")
	private List<TvShowGenresItem> genres;

	public List<TvShowGenresItem> getGenres(){
		return genres;
	}

	@NonNull
	@Override
 	public String toString(){
		return 
			"TvShowGenreResponse{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}