package com.atiga.moviecataloguerl.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvShowResultsItem {

	@SerializedName("genre_text")
	private String genreText;

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<Object> originCountry;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	public TvShowResultsItem( int id, String name,String genreText, String firstAirDate, String overview, String posterPath, String backdropPath, double voteAverage) {
		this.genreText = genreText;
		this.firstAirDate = firstAirDate;
		this.overview = overview;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.voteAverage = voteAverage;
		this.name = name;
		this.id = id;
	}

	public String getGenreText() {
		return genreText;
	}

	public void setGenreText(String genreText) {
		this.genreText = genreText;
	}

	public void setFirstAirDate(String firstAirDate){
		this.firstAirDate = firstAirDate;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setOriginCountry(List<Object> originCountry){
		this.originCountry = originCountry;
	}

	public List<Object> getOriginCountry(){
		return originCountry;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setOriginalName(String originalName){
		this.originalName = originalName;
	}

	public String getOriginalName(){
		return originalName;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setVoteAverage(int voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return 
			"TvShowResultsItem{" +
			"first_air_date = '" + firstAirDate + '\'' + 
			",overview = '" + overview + '\'' + 
			",original_language = '" + originalLanguage + '\'' + 
			",genre_ids = '" + genreIds + '\'' + 
			",poster_path = '" + posterPath + '\'' + 
			",origin_country = '" + originCountry + '\'' + 
			",backdrop_path = '" + backdropPath + '\'' + 
			",original_name = '" + originalName + '\'' + 
			",popularity = '" + popularity + '\'' + 
			",vote_average = '" + voteAverage + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",vote_count = '" + voteCount + '\'' + 
			"}";
		}
}