package com.atiga.moviecataloguerl.model;

import com.google.gson.annotations.SerializedName;

public class MovieGenresItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public MovieGenresItem(String name, int id) {
		this.name = name;
		this.id = id;
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

	@Override
 	public String toString(){
		return 
			"MovieGenresItem{" +
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}