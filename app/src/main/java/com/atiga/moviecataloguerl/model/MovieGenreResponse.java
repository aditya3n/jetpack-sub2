package com.atiga.moviecataloguerl.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieGenreResponse{

	@SerializedName("genres")
	private List<MovieGenresItem> genres;

	public void setGenres(List<MovieGenresItem> genres){
		this.genres = genres;
	}

	public List<MovieGenresItem> getGenres(){
		return genres;
	}

	@Override
 	public String toString(){
		return 
			"MovieGenreResponse{" + 
			"genres = '" + genres + '\'' + 
			"}";
		}
}