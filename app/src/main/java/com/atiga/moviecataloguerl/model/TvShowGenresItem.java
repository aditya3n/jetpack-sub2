package com.atiga.moviecataloguerl.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TvShowGenresItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public TvShowGenresItem(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName(){
		return name;
	}

    public int getId(){
		return id;
	}

	@NonNull
	@Override
 	public String toString(){
		return 
			"TvShowGenresItem{" +
			"name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}

}